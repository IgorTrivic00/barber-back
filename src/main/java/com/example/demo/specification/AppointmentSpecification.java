package com.example.demo.specification;

import com.example.demo.dto.filter.AppointmentFilter;
import com.example.demo.model.AppointmentEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSpecification {

    public static Specification<AppointmentEntity> search(AppointmentFilter filter){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filter.uuidsIn()
                    .filter(uuids -> !uuids.isEmpty())
                    .map(uuids -> root.get("uuid").in(uuids))
                    .ifPresent(predicates::add);

            filter.customerUuids()
                    .filter(customerUuids -> !customerUuids.isEmpty())
                    .map(customerUuids -> root.get("customer").get("uuid").in(customerUuids))
                    .ifPresent(predicates::add);

            filter.barberUuids()
                    .filter(barberUuids -> !barberUuids.isEmpty())
                    .map(barberUuids -> root.get("barber").get("uuid").in(barberUuids))
                    .ifPresent(predicates::add);

            filter.states()
                    .filter(states -> !states.isEmpty())
                    .map(states -> root.get("appointmentState").in(states))
                    .ifPresent(predicates::add);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
