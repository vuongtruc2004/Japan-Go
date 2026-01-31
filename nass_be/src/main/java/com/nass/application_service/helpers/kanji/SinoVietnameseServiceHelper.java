package com.nass.application_service.helpers.kanji;

import com.nass.application_service.dtos.entries.SinoVietnameseEntry;
import com.nass.application_service.dtos.requests.kanji.GetSinoVietnameseRequest;
import com.nass.application_service.importers.kanji.MainSinoVietnameseXlsxImport;
import com.nass.application_service.importers.kanji.SinoVietnameseJsonImporter;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.kanji.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SinoVietnameseServiceHelper {

    private final KanjiRepository kanjiRepository;
    private final SinoVietnameseJsonImporter sinoVietnameseJsonImporter;
    private final MainSinoVietnameseXlsxImport mainSinoVietnameseXlsxImport;

    public List<KanjiEntity> importSinoVietnamese(List<InputStream> sinoVietnameseInputstreamList) {
        Map<String, List<SinoVietnameseEntry>> sinoVietnameseEntryMap = new HashMap<>();
        for (InputStream sinoVietnameseInputstream : sinoVietnameseInputstreamList) {
            sinoVietnameseJsonImporter.importSinoVietnamese(sinoVietnameseInputstream, sinoVietnameseEntryMap);
        }
        return sinoVietnameseJsonImporter.updateKanjiWithSinoVietnamese(sinoVietnameseEntryMap);
    }

    public List<KanjiEntity> importMainSinoVietnamese(InputStream mainSinoVietnameseInputstream) {
        Map<String, String> mainSinoVietnameseMap = mainSinoVietnameseXlsxImport.importMainSinoVietnamese(mainSinoVietnameseInputstream);
        return mainSinoVietnameseXlsxImport.updateKanjiWithMainSinoVietnamese(mainSinoVietnameseMap);
    }

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
