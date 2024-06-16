package com.alcity.utility;

import com.alcity.entity.base.PuzzleCategory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification implements Specification<PuzzleCategory> {
    private SearchCriteria criteria;
    public SearchSpecification(SearchCriteria criteria){
        this.criteria =criteria;
    }

    @Override
    public Predicate toPredicate(Root<PuzzleCategory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getColumn()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.<String>get(criteria.getColumn())), "%" + criteria.getValue() + "%");
            }
        }
        return null;
    }
}
