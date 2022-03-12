package com.example.quarantinefinder.constant;

import java.lang.reflect.ParameterizedType;
import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ahmed Khalil
 * @param <E>   entity
 * @param <REQ> requestObject
 * @param <RES> responseObject
 */
public interface AbstractMapper<E, REQ, RES> {

	E toEntity(REQ request);

	abstract void toEntity(REQ request, E entity);

	default Comparator<RES> getDefaultComparator() {
		return null;
	}

	default RES toResponse(E entity, ResponseLevel level) {

		switch (level) {
		case BASIC:
			return toBasicResponse(entity);
		case SIMPLE:
			return toSimpleResponse(entity);
		case DETAILS:
			return toDetailsResponse(entity);
		case FULL:
			return toFullResponse(entity);
		default:
			break;
		}
		throw Exceptions.badRequest("unknown response-leve {}", "level", level, level);
	}

	default RES toResponse(E entity) {
		return toResponse(entity, ResponseLevel.BASIC);
	}

	abstract RES toBasicResponse(E entity);

	default RES toSimpleResponse(E entity) {
		return toBasicResponse(entity);
	}

	default RES toSimpleResponse(E entity, RES response) {
		return toBasicResponse(entity);
	}

	default RES toDetailsResponse(E entity) {
		return toSimpleResponse(entity);
	}

	default RES toFullResponse(E entity) {
		return toDetailsResponse(entity);
	}

	default List<RES> toResponses(List<E> list, ResponseLevel level) {
		List<RES> dtos = new LinkedList<>();
		if (list == null)
			return null;
		for (E entity : list) {
			RES dto = toResponse(entity, level);
			dtos.add(dto);
		}
		if (getDefaultComparator() != null)
			dtos.sort(getDefaultComparator());
		return dtos;
	}

	default Date toDate(Long millis) {

		if (millis == null) {
			return null;
		}
		return new Date(millis);

	}

	default Long toMillis(Date date) {
		if (date == null) {
			return null;
		}
		return date.getTime();
	}

	default Date now() {
		return Date.from(Instant.now());
	}

//	default long getUIDFromName(String name) {
//		return Utils.DJBHash(name);
//	}

	@SuppressWarnings("unchecked")
	default String getEntityName() {

		String fullEntityClassName = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]).getTypeName();

		var names = fullEntityClassName.split("\\.");
		return names[names.length - 1];
	}

	default <X> List<X> list(List<X> original) {
		return original == null ? new LinkedList<>() : original;
	}

	default <X> Stream<X> stream(Collection<X> c) {
		return c == null ? Stream.empty() : c.stream();
	}

}
