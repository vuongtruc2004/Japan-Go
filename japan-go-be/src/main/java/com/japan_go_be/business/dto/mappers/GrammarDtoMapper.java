package com.japan_go_be.business.dto.mappers;

import com.japan_go_be.business.dto.responses.grammar.*;
import com.japan_go_be.business.dto.responses.sentence.SentenceResponse;
import com.japan_go_be.infrastructure.entities.grammar.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GrammarDtoMapper {
    private final SentenceDtoMapper sentenceDtoMapper;

    public GrammarResponse grammarEntityToGrammarResponseSummary(GrammarEntity grammarEntity) {
        return GrammarResponse.builder()
                .id(grammarEntity.getId())
                .grammarTitle(grammarEntity.getGrammarTitle())
                .grammarTitleFurigana(grammarEntity.getGrammarTitleFurigana())
                .grammarTitleRomaji(grammarEntity.getGrammarTitleRomaji())
                .translation(grammarEntity.getTranslation())
                .jlptLevel(grammarEntity.getJlptLevel())
                .build();
    }

    public GrammarResponse grammarEntityToGrammarResponseDetails(GrammarEntity grammarEntity) {
        GrammarResponse grammarResponse = grammarEntityToGrammarResponseSummary(grammarEntity);
        grammarResponse.setGrammarMeaning(grammarMeaningEntityToGrammarMeaningResponse(grammarEntity.getGrammarMeaning()));
        grammarResponse.setGrammarStructure(grammarStructureEntityToGrammarStructureResponse(grammarEntity.getGrammarStructure()));
        grammarResponse.setGrammarExample(grammarExampleEntityToGrammarExampleResponse(grammarEntity.getGrammarExample()));
        grammarResponse.setGrammarNote(grammarNoteEntityToGrammarNoteResponse(grammarEntity.getGrammarNote()));
        return grammarResponse;
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
