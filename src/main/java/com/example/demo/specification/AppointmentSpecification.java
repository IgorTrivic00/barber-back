package com.example.demo.specification;

import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.model.AppointmentEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSpecification {

    public static Specification<AppointmentEntity> search(AppointmentFilter filter) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filter.barberUuids()
                    .filter(barberUuids -> !barberUuids.isEmpty())
                    .map(barberUuids -> root.get("barber").get("uuid").in(barberUuids))
                    .ifPresent(predicates::add);

            filter.startTime()
                    .map(startTime -> criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), startTime))
                    .ifPresent(predicates::add);

            filter.endTime()
                    .map(endTime -> criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), endTime))
                    .ifPresent(predicates::add);

            filter.status()
                    .map(status -> criteriaBuilder.equal(root.get("status"), status))
                    .ifPresent(predicates::add);

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }

}
