package com.japan_go_be.business.dto.mappers;

import com.japan_go_be.business.dto.responses.card.DeckResponse;
import com.japan_go_be.infrastructure.entities.card.DeckEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeckDtoMapper {

    public DeckResponse deckEntityToDeckResponse(DeckEntity deckEntity) {
        return DeckResponse.builder()
                .id(deckEntity.getId())
                .title(deckEntity.getTitle())
                .description(deckEntity.getDescription())
                .build();
    }
}
