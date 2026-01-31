package com.nass.application_service.helpers.kanji;

import com.nass.application_service.importers.kanji.KanjiXmlImporter;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KanjiServiceHelper {
    private final KanjiXmlImporter kanjiXmlImporter;

    public List<KanjiEntity> importKanjiFromKanjidic(InputStream kanjidicInputstream, InputStream kanjiJlptInputstream) {
        return kanjiXmlImporter.importKanji(kanjidicInputstream, kanjiJlptInputstream);
    }
}
