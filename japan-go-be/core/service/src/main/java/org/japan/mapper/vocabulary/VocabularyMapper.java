package org.japan.mapper.vocabulary;

import org.japan.dto.response.vocabulary.VocabularyResponse;
import org.japan.entity.vocabulary.VocabularyEntity;
import org.japan.helper.kanji.KanjiHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {SupportVocabularyMapper.class}
)
public abstract class VocabularyMapper {

    @Autowired
    protected KanjiHelper kanjiHelper;

    @Mapping(target = "kanjiVgList",
            expression = "java(mapJapaneseToKanjiVgList(entity.getJapanese()))")
    public abstract VocabularyResponse mapEntityToResponseDetails(VocabularyEntity entity);

    protected List<String> mapJapaneseToKanjiVgList(String japanese) {
        if (japanese == null) {
            return List.of();
        }

        List<String> kanjiVgList = new ArrayList<>();
        for (char c : japanese.toCharArray()) {
            kanjiVgList.add(kanjiHelper.getSvgOfKanjiCharacter(String.valueOf(c)));
        }
        return kanjiVgList;
    }
}
