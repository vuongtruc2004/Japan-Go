package org.japan.importer.kanji;

import lombok.RequiredArgsConstructor;
import org.japan.i18n.I18nService;
import org.japan.persistence.repository.kanji.KanjiPageRepository;
import org.japan.persistence.repository.kanji.KanjiRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KanjiPageXlsxImporter {

    private final I18nService i18nService;
    private final KanjiPageRepository kanjiPageRepository;
    private final KanjiRepository kanjiRepository;


}
