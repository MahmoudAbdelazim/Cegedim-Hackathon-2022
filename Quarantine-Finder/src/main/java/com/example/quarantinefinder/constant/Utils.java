package com.example.quarantinefinder.constant;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

	public static boolean isNullOrEmpty(Object instance) {
		if (instance == null) {
			return true;
		}

		if (instance instanceof Collection<?>) {
			return ((Collection<?>) instance).isEmpty();
		}
		if (instance instanceof Map<?, ?>) {
			return ((Map<?, ?>) instance).isEmpty();
		}
		if (instance instanceof CharSequence) {
			return instance.toString().trim().isEmpty();
		}
		return false;
	}

	public static String format(String template, Object... args) {
		Objects.requireNonNull(template, "template can't be null");
		Pattern pattern = Pattern.compile("\\{\\}");
		long placeHoderCounts = pattern.matcher(template).results().count();
		int argsLength = args == null ? 0 : args.length;

		if (argsLength == 0) {
			return template;
		}

		for (Object arg : args) {
			template = template.replaceFirst("\\{\\}", toString(arg).orElse("null"));
		}
		return template;
	}

	public static Optional<String> toString(Object argument) {
		if (argument == null) {
			return Optional.empty();
		}
		if (argument instanceof Object[]) {
			argument = Arrays.asList((Object[]) argument);
		}
		return Optional.ofNullable(argument.toString());
	}

	@AllArgsConstructor
	public static class Diff<E, R> {

		public final List<R> toBeCreated;
		public final List<R> toBeUpdated;
		public final List<R> toBeAdded;
		public final List<E> toBeDeleted;
	}

	public static <E, R, I> Diff<E, R> difference(Collection<E> entitys, Function<E, I> entityFunction,
			Collection<R> requests, Function<R, I> requestFunction) {

		Set<I> entityIndex = entitys.stream().map(entityFunction).collect(Collectors.toSet());
		Set<I> requestIndex = requests.stream().map(requestFunction).filter(Objects::nonNull)
				.collect(Collectors.toSet());

		List<R> toBeUpdate = new LinkedList<>();
		List<R> toBeCreated = new LinkedList<>();
		List<R> toBeAdded = new LinkedList<>();

		for (var request : requests) {

			I requestIdentity = requestFunction.apply(request);

			if (requestIdentity == null) {
				toBeCreated.add(request);
			} else {
				if (entityIndex.contains(requestIdentity)) {
					toBeUpdate.add(request);
				} else {
					toBeAdded.add(request);
				}
			}
		}
		List<E> toBeDeleted = entitys.stream().filter(e -> !requestIndex.contains(entityFunction.apply(e)))
				.collect(Collectors.toList());

		return new Diff<>(toBeCreated, toBeUpdate, toBeAdded, toBeDeleted);

	}

	public static <E> List<E> asList(@SuppressWarnings("unchecked") E... values) {
		
		if (values == null || values.length == 0) {
			return new LinkedList<>();
		}
		return new LinkedList<>(Arrays.asList(values));
	}
	
//	public static synchronized int DJBHash(String str)
//	{
//		int hash = 5381;
//		for (int i = 0; i < str.length(); i++)
//		{
//			hash = ((hash << 5) + hash) + str.charAt(i);
//		}
//		return (hash & 0x7FFFFFFF);
//	}
}
