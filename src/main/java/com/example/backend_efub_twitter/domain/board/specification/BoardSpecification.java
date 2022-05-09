package com.example.backend_efub_twitter.domain.board.specification;

import com.example.backend_efub_twitter.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BoardSpecification implements Specification<Board> {
    private final BoardSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getUserId() != null){
            predicates.add(criteriaBuilder.equal(root.join("user").get("id"), criteria.getUserId()));
        }

        if (criteria.getHashTagKeyword() != null){
            criteria.getHashTagKeyword().forEach(
                    hashtagKeyword -> predicates.add(criteriaBuilder.equal(
                            root.join("boardHashTag").join("hashTag").get("keyword"),
                            hashtagKeyword
                    ))
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
