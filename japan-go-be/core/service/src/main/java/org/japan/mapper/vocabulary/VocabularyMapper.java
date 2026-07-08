package org.japan.mapper.vocabulary;

import org.japan.dto.response.vocabulary.VocabularyResponse;
import org.japan.entity.vocabulary.VocabularyEntity;
import org.japan.helper.kanji.KanjiHelper;
import org.japan.helper.kanji.SinoVietnameseHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
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

    @Autowired
    private SinoVietnameseHelper sinoVietnameseHelper;

    @Mapping(target = "kanjiVgList",
            expression = "java(getKanjiVgListFromJapanese(entity.getJapanese()))")
    @Mapping(target = "sinoVietnamese",
            source = "japanese",
            qualifiedByName = "sinoVietnamese")
    public abstract VocabularyResponse mapEntityToResponseDetails(VocabularyEntity entity);

    protected List<String> getKanjiVgListFromJapanese(String japanese) {
        if (japanese == null) {
            return List.of();
        }

        List<String> kanjiVgList = new ArrayList<>();
        for (char c : japanese.toCharArray()) {
            String kanjiVg = kanjiHelper.getSvgOfKanjiCharacter(String.valueOf(c));
            if (kanjiVg != null) {
                kanjiVgList.add(kanjiVg);
            }
        }
        return kanjiVgList;
    }

    @Named("sinoVietnamese")
    protected String getSinoVietnameseFromJapanese(String japanese) {
        if (japanese == null) {
            return null;
        }
        return sinoVietnameseHelper.getSinoVietnameseOfKanji(japanese);
    }
}
