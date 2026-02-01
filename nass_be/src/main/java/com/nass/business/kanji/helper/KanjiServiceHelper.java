package com.nass.business.kanji.helper;

import com.nass.business.kanji.entity.KanjiEntity;
import com.nass.business.kanji.importer.KanjiXmlImporter;
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
