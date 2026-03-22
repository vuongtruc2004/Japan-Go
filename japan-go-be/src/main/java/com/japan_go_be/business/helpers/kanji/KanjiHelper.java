package com.japan_go_be.business.helpers.kanji;

import com.japan_go_be.business.exceptions.FileNotValidException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.contract.constants.message.FileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class KanjiHelper {

    private final I18nService i18nService;

    @Value("${sources.uri}")
    private String sourcesUri;

    /**
     * Returns the SVG content of a given Kanji character.
     * This method finds the corresponding SVG file (based on the Unicode
     * code point of the character) inside the "kanjivg" directory.
     * It reads the file and returns only the <svg>...</svg> content.
     *
     * @param kanjiCharacter a single Kanji character such as 私
     * @return SVG content as a String, or null if the file does not exist
     * @throws FileNotValidException if an error occurs while reading the file
     */

    public String getSvgOfKanjiCharacter(String kanjiCharacter) {
        String file = String.format("%05x.svg", kanjiCharacter.codePointAt(0));

        Path basePath = Paths.get(URI.create(sourcesUri));
        Path svgPath = basePath.resolve("kanjivg").resolve(file);

        if (!Files.exists(svgPath)) {
            return null;
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
