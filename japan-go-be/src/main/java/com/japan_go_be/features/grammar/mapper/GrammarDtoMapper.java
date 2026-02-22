package com.japan_go_be.features.grammar.mapper;

import com.japan_go_be.features.grammar.dto.response.*;
import com.japan_go_be.features.grammar.entity.*;
import com.japan_go_be.features.sentence.dto.response.SentenceResponse;
import com.japan_go_be.features.sentence.mapper.SentenceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GrammarDtoMapper {
    private final SentenceDtoMapper sentenceDtoMapper;

    public GrammarResponse grammarEntityToGrammarResponseDetails(GrammarEntity grammarEntity) {
        return GrammarResponse.builder()
                .id(grammarEntity.getId())
                .grammarTitle(grammarEntity.getGrammarTitle())
                .grammarMeaning(grammarMeaningEntityToGrammarMeaningResponse(grammarEntity.getGrammarMeaning()))
                .grammarStructure(grammarStructureEntityToGrammarStructureResponse(grammarEntity.getGrammarStructure()))
                .grammarExample(grammarExampleEntityToGrammarExampleResponse(grammarEntity.getGrammarExample()))
                .grammarNote(grammarNoteEntityToGrammarNoteResponse(grammarEntity.getGrammarNote()))
                .build();

    }

    public GrammarMeaningResponse grammarMeaningEntityToGrammarMeaningResponse(GrammarMeaningEntity grammarMeaningEntity) {
        GrammarMeaningResponse grammarMeaningResponse = GrammarMeaningResponse.builder()
                .id(grammarMeaningEntity.getId())
                .build();
        List<SentenceResponse> sentenceResponses = grammarMeaningEntity.getSentences()
                .stream()
                .map(sentenceDtoMapper::sentenceEntityToSentenceResponseDetails)
                .toList();

        grammarMeaningResponse.setSentences(sentenceResponses);
        return grammarMeaningResponse;
    }

    public GrammarStructureResponse grammarStructureEntityToGrammarStructureResponse(GrammarStructureEntity grammarStructureEntity) {
        GrammarStructureResponse grammarMeaningResponse = GrammarStructureResponse.builder()
                .id(grammarStructureEntity.getId())
                .build();
        List<SentenceResponse> sentenceResponses = grammarStructureEntity.getSentences()
                .stream()
                .map(sentenceDtoMapper::sentenceEntityToSentenceResponseDetails)
                .toList();

        grammarMeaningResponse.setSentences(sentenceResponses);
        return grammarMeaningResponse;
    }

    public GrammarExampleResponse grammarExampleEntityToGrammarExampleResponse(GrammarExampleEntity grammarExampleEntity) {
        GrammarExampleResponse grammarMeaningResponse = GrammarExampleResponse.builder()
                .id(grammarExampleEntity.getId())
                .build();
        List<SentenceResponse> sentenceResponses = grammarExampleEntity.getSentences()
                .stream()
                .map(sentenceDtoMapper::sentenceEntityToSentenceResponseDetails)
                .toList();

        grammarMeaningResponse.setSentences(sentenceResponses);
        return grammarMeaningResponse;
    }

    public GrammarNoteResponse grammarNoteEntityToGrammarNoteResponse(GrammarNoteEntity grammarNoteEntity) {
        GrammarNoteResponse grammarMeaningResponse = GrammarNoteResponse.builder()
                .id(grammarNoteEntity.getId())
                .build();
        List<SentenceResponse> sentenceResponses = grammarNoteEntity.getSentences()
                .stream()
                .map(sentenceDtoMapper::sentenceEntityToSentenceResponseDetails)
                .toList();

        grammarMeaningResponse.setSentences(sentenceResponses);
        return grammarMeaningResponse;
    }
}
