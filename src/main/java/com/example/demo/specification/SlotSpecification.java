package com.example.demo.specification;

import com.example.demo.dto.filter.SlotFilter;
import com.example.demo.model.SlotEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SlotSpecification {

    public static Specification<SlotEntity> search(SlotFilter slotFilter){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            slotFilter.barberUuids()
                    .filter(barberUuids -> !barberUuids.isEmpty())
                    .map(barberUuids -> root.get("barber").get("uuid").in(barberUuids))
                    .ifPresent(predicates::add);

            slotFilter.from()
                    .map(from -> criteriaBuilder.greaterThanOrEqualTo(root.get("start"), from))
                    .ifPresent(predicates::add);

            slotFilter.to()
                    .map(to -> criteriaBuilder.lessThanOrEqualTo(root.get("end"), to))
                    .ifPresent(predicates::add);

            slotFilter.states()
                    .filter(states -> !states.isEmpty())
                    .map(states -> root.get("slotState").in(states))
                    .ifPresent(predicates::add);

            query.orderBy(criteriaBuilder.asc(root.get("start")));

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }

}
