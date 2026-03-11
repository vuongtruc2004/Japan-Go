package com.japan_go_be.business.specifications.grammar;

import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrammarSpecification {
    public Specification<GrammarEntity> hasSearchKey(String searchKey) {
        return (root, query, builder) -> {
            if (searchKey == null || searchKey.isBlank()) {
                return builder.conjunction();
            }

            String keyword = "%" + searchKey.trim().toLowerCase() + "%";

            return builder.or(
                    builder.like(builder.lower(root.get("grammarTitle")), keyword),
                    builder.like(builder.lower(root.get("grammarTitleFurigana")), keyword),
                    builder.like(builder.lower(root.get("grammarTitleRomaji")), keyword),
                    builder.like(builder.lower(root.get("translation")), keyword)
            );
        };
    }
}
