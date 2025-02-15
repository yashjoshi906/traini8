package co.traini8.util;

import org.springframework.data.jpa.domain.Specification;

import co.traini8.entity.TrainingCenter;

public class CenterSpecs {
	
	public static Specification<TrainingCenter> containsCenterName(String name) {

		return (root, query, cb) -> cb.like(cb.lower(root.get("centerName")), "%" + name.toLowerCase() + "%");
	}
	public static Specification<TrainingCenter> containsCenterCode(String code) {
		
		return (root, query, cb) -> cb.like(cb.lower(root.get("centerCode")), "%" + code.toLowerCase() + "%");
	}

}	
