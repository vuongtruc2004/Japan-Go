package com.nass.business.kanji.helper;

import com.nass.business.kanji.entity.KanjiEntity;
import com.nass.business.kanji.importer.KanjiXmlImporter;
import com.nass.common.constant.FileMessage;
import com.nass.common.exception.FileNotValidException;
import com.nass.common.exception.NotFoundException;
import com.nass.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KanjiHelper {

    private final KanjiXmlImporter kanjiXmlImporter;
    private final I18nService i18nService;

    @Value("${sources.uri}")
    private String sourcesUri;

    public List<KanjiEntity> importKanjiFromKanjidic(InputStream kanjidicInputstream, InputStream kanjiJlptInputstream) {
        return kanjiXmlImporter.importKanji(kanjidicInputstream, kanjiJlptInputstream);
    }

    public String getSvgOfKanjiCharacter(String kanjiCharacter) {
        String file = String.format("%05x.svg", kanjiCharacter.codePointAt(0));

        Path basePath = Paths.get(URI.create(sourcesUri));
        Path svgPath = basePath.resolve("kanjivg").resolve(file);

        if (!Files.exists(svgPath)) {
            throw new NotFoundException(
                    i18nService.translation(FileMessage.FILE_NOT_FOUND),
                    i18nService.translation(FileMessage.FILE_NOT_FOUND)
            );
        }

        try {
            String raw = Files.readString(svgPath);

            int start = raw.indexOf("<svg");
            int end = raw.lastIndexOf("</svg>");

            if (start == -1 || end == -1) {
                return raw;
            }

            return raw.substring(start, end + 6);

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }
}
