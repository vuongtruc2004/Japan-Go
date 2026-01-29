package com.nass.application_service.helpers.kanji;

import com.nass.application_service.dtos.requests.kanji.GetSinoVietnameseRequest;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SinoVietnameseServiceHelper {

    private final KanjiRepository kanjiRepository;

    /**
     * @param getSinoVietnameseRequest an object contains kanji list (in string type)
     *                                 for example: 私\n料理\n...
     *                                 and the divider (to split the kanji list string)
     * @return array of kanji after split by the divider
     */
    public String[] getKanjiArray(GetSinoVietnameseRequest getSinoVietnameseRequest) {
        String kanjiArrayRaw = getSinoVietnameseRequest.kanjiArrayRaw();
        String divider = getSinoVietnameseRequest.divider();
        return switch (divider) {
            case "line" -> kanjiArrayRaw.split("\n");
            case "whitespace" -> kanjiArrayRaw.split("\\s+");
            default -> kanjiArrayRaw.split(divider);
        };
    }

    /**
     * this method will get the sino vietnamese of a kanji string
     * and skip kanji all character in the parentheses "（）"
     *
     * @param kanji the kanji string such as 私は料理が好きだ
     * @return the sino vietnamese such as "TỰ LIỆU LÍ HẢO"
     */
    public String getSinoVietnameseOfKanji(String kanji) {
        StringBuilder mainSinoVietnamese = new StringBuilder();
        int length = kanji.length();
        for (int i = 0; i < length; i++) {
            char ch = kanji.charAt(i);
            if (ch == '(') {
                do i++;
                while (i < length && kanji.charAt(i) != ')');
                continue;
            }

            if (ch == '（') {
                do i++;
                while (i < length && kanji.charAt(i) != '）');
                continue;
            }
            kanjiRepository.findByKanjiCharacter(String.valueOf(ch))
                    .ifPresent(kanjiEntity ->
                    {
                        SinoVietnameseEntity mainSinoVietnameseEntity = kanjiEntity.getMainSinoVietnamese();
                        if (mainSinoVietnameseEntity != null) {
                            mainSinoVietnamese.append(mainSinoVietnameseEntity.getReadingText()).append(" ");
                        }
                    });
        }
        return mainSinoVietnamese.toString().trim();
    }
}
