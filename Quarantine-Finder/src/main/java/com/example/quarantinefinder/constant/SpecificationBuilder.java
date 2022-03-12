package com.example.quarantinefinder.constant;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public abstract class SpecificationBuilder<T> implements Specification<T> {

	private static final long serialVersionUID = 1L;

	protected transient Root<T> root;
	protected transient CriteriaQuery<?> query;
	protected transient CriteriaBuilder criteriaBuilder;
	protected transient List<Predicate> expressions = new LinkedList<>();

	protected transient List<Predicate> orExpressions = new LinkedList<>();

	protected transient List<Predicate> orPredicates = new LinkedList<>();

	private boolean inOr = false;

	private transient Set<Root<?>> roots = new HashSet<>();
	private transient Set<Join<?, ?>> joins = new HashSet<>();

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		this.root = root;
		this.query = query;
		this.criteriaBuilder = criteriaBuilder;
		query.distinct(true);
		Predicate predicate = criteriaBuilder.conjunction();
		fillExpressions();

		predicate.getExpressions().addAll(expressions);

		orPredicates.add(predicate);

		return criteriaBuilder.and(orPredicates.toArray(Predicate[]::new));

	}

	protected abstract void fillExpressions();

	protected void or(Runnable runner) {

		inOr = true;

		runner.run();

		if (!orExpressions.isEmpty()) {
			Predicate[] orArray = orExpressions.toArray(Predicate[]::new);
			orPredicates.add(criteriaBuilder.or(orArray));
		}
		orExpressions.clear();

		inOr = false;
	}

	protected Holder of(String name) {
		var holder = new Holder();
		holder.name = name;
		holder.nullable = false;
		return holder;
	}

	public Joiner join(String name) {
		return new Joiner().join(name);
	}

	public Joiner left(String name) {
		return new Joiner().left(name);
	}

	public Joiner right(String name) {
		return new Joiner().right(name);
	}

	public Joiner join(Class<?> entityClass) {
		return new Joiner().join(entityClass);
	}

	public class Joiner {

		private Root<?> left = root;
		private Root<?> right = root;
		private Join<Object, Object> join;

		public Joiner join(String attributeName) {

			return join(attributeName, JoinType.INNER);
		}

		public Joiner left(String attributeName) {

			return join(attributeName, JoinType.LEFT);
		}

		public Joiner right(String attributeName) {

			return join(attributeName, JoinType.RIGHT);
		}

		private Joiner join(String attributeName, JoinType joinType) {

			if (join != null) {
				join = join.join(attributeName, joinType);
			}
			else if (right != null) {
				join = right.join(attributeName, joinType);
			}
			else {
				join = left.join(attributeName, joinType);
			}
			joins.add(join);
			return this;
		}

		public Joiner on(String field1Name, String field2Name) {

			if (join != null) {
				var currentRoot = right == null ? left : right;

				joins.remove(join);
				join = join.on(criteriaBuilder.equal(currentRoot.get(field1Name), join.get(field2Name)));
				joins.add(join);
			}
			else {
				var exp = criteriaBuilder.equal(left.get(field1Name), right.get(field2Name));
				doAdd(exp);
			}
			return this;
		}

		public Joiner join(Class<?> entityClass) {
			join = null;
			left = right;
			right = query.from(entityClass);
			roots.add(right);

			return this;
		}

		public Holder of(String name) {

			JoinHolder holder = new JoinHolder();
			holder.currentRoot = join == null ? right : join;
			holder.name = name;
			holder.nullable = false;

			return holder;
		}

	}

	public class JoinHolder extends Holder {
		private Path<?> currentRoot;

		@Override
		protected <P> Path<P> path() {
			return currentRoot.get(name);
		}

	}

	public class Holder {

		protected boolean nullable;
		protected String name;

		protected <P> Path<P> path() {
			String[] parts = name.split("\\.");
			if (parts.length == 0) {
				return root.get(name);
			}
			else {
				Path<P> path = root.get(parts[0]);
				for (int i = 1; i < parts.length; i++) {
					path = path.get(parts[i]);
				}
				return path;
			}
		}

		public void is(Object value) {
			if (isAcceptable(value)) {
				doAdd(criteriaBuilder.equal(path(), value));
			}
		}

		public void is(Supplier<Object> supplier) {
			Object value = null;
			try {
				value = supplier.get();
			}
			catch (Exception e) {

			}

			if (isAcceptable(value)) {
				doAdd(criteriaBuilder.equal(path(), value));
			}
		}

		public void is(Optional<Object> supplier) {
			supplier.ifPresent(value -> {
				if (isAcceptable(value)) {
					doAdd(criteriaBuilder.equal(path(), value));
				}
			});

		}

		public void isFalse() {
			is(false);
		}

		public void isTrue() {
			is(true);
		}

		public void isNull() {
			doAdd(criteriaBuilder.isNull(path()));
		}

		public void isNotNull() {
			doAdd(criteriaBuilder.isNotNull(path()));
		}

		public void isNot(Object value) {
			if (isAcceptable(value)) {
				doAdd(criteriaBuilder.notEqual(path(), value));
			}
		}

		public void in(Collection<?> collection) {
			if (isAcceptable(collection)) {
				if (collection.size() > 1) {
					doAdd(path().in(collection));
				}
				else {
					doAdd(criteriaBuilder.equal(path(), collection.stream().findFirst().orElse(null)));
				}
			}
		}

		public void date(Long from, Long to) {

			if (from != null && to != null) {
				doAdd(criteriaBuilder.between(path(), new Date(from), new Date(to)));
			}
			else if (from != null) {
				doAdd(criteriaBuilder.greaterThanOrEqualTo(path(), new Date(from)));
			}
			else if (to != null) {
				doAdd(criteriaBuilder.lessThanOrEqualTo(path(), new Date(to)));
			}

		}

		public void localDateTime(Long from, Long to) {
			if (from != null && to != null) {
				doAdd(criteriaBuilder.between(path(), toDateTime(from), toDateTime(to)));
			}
			else if (from != null) {
				doAdd(criteriaBuilder.greaterThanOrEqualTo(path(), toDateTime(from)));
			}
			else if (to != null) {
				doAdd(criteriaBuilder.lessThanOrEqualTo(path(), toDateTime(to)));
			}

		}

		public void like(String text) {
			if (isAcceptable(text)) {
				var lower = criteriaBuilder.lower(path());
				String regex = text == null ? null : "%" + text.toLowerCase() + "%";
				doAdd(criteriaBuilder.like(lower, regex));
			}
		}

		public void isGreaterThan(Number value) {
			if (value != null) {
				doAdd(criteriaBuilder.greaterThan(path(), value.doubleValue()));
			}
		}

		public void isGreaterThanOrEqualTo(Number value) {
			if (value != null) {
				doAdd(criteriaBuilder.greaterThanOrEqualTo(path(), value.doubleValue()));
			}
		}

		public void isLessThan(Number value) {
			if (value != null) {
				doAdd(criteriaBuilder.lessThan(path(), value.doubleValue()));
			}
		}

		public void isLessThanOrEqualTo(Number value) {
			if (value != null) {
				doAdd(criteriaBuilder.lessThanOrEqualTo(path(), value.doubleValue()));
			}
		}

		private boolean isAcceptable(Object value) {
			return nullable || (!Utils.isNullOrEmpty(value));
		}

	}

	private void doAdd(Predicate expression) {
		if (inOr) {
			orExpressions.add(expression);
		}
		else {
			expressions.add(expression);
		}
	}

	protected Path<?> getMatching(Class<?> klass) {
		var matchingRoot = roots.stream().filter(r -> r.getJavaType().equals(klass)).findAny().orElse(null);
		var matchingJoin = joins.stream().filter(j -> j.getJavaType().equals(klass)).findAny().orElse(null);
		if (matchingRoot != null) {
			return matchingRoot;
		}
		if (matchingJoin != null) {
			return matchingJoin;
		}
		return root;
	}

	public static LocalDateTime toDateTime(Long millis) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
	}

}
