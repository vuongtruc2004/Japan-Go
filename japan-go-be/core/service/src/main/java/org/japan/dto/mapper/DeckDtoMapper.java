package org.japan.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.japan.dto.response.card.DeckResponse;
import org.japan.entity.card.DeckEntity;
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
