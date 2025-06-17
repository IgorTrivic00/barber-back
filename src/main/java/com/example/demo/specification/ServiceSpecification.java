package com.example.demo.specification;

import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.model.ServiceEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ServiceSpecification {

    public static Specification<ServiceEntity> search(ServiceFilter filter){

        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filter.uuidsIn()
                    .filter(uuidsIn -> !uuidsIn.isEmpty())
                    .map(uuidsIn -> root.get("uuid").in(uuidsIn))
                    .ifPresent(predicates::add);


            filter.barberUuids()
                    .filter(barberUuids -> !barberUuids.isEmpty())
                    .map(barberUuids -> root.get("barber").get("uuid").in(barberUuids))
                    .ifPresent(predicates::add);

            query.orderBy(criteriaBuilder.asc(criteriaBuilder.lower(root.get("serviceName"))));

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });

    }

}
