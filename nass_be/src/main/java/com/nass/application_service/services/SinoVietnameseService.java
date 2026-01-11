package com.nass.application_service.services;

import com.nass.application_service.dto.entry.SinoVietnameseEntry;
import com.nass.application_service.dto.mapper.KanjiMapper;
import com.nass.application_service.dto.request.GetSinoVietnameseRequest;
import com.nass.application_service.dto.response.KanjiResponse;
import com.nass.application_service.importers.MainSinoVietnameseXlsxImport;
import com.nass.application_service.importers.SinoVietnameseJsonImporter;
import com.nass.application_service.services.interfaces.ISinoVietnameseService;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.entities.kanji.SinoVietnameseEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SinoVietnameseService implements ISinoVietnameseService {
    private final SinoVietnameseJsonImporter sinoVietnameseJsonImporter;
    private final KanjiRepository kanjiRepository;
    private final KanjiMapper kanjiMapper;
    private final MainSinoVietnameseXlsxImport mainSinoVietnameseXlsxImport;

    @Override
    public String getSinoVietnameseOfKanji(GetSinoVietnameseRequest getSinoVietnameseRequest) {
        String kanjiArrayRaw = getSinoVietnameseRequest.kanjiArrayRaw();
        String divider = getSinoVietnameseRequest.divider();
        String[] kanjiArray = switch (divider) {
            case "line" -> kanjiArrayRaw.split("\n");
            case "whitespace" -> kanjiArrayRaw.split("\\s+");
            default -> kanjiArrayRaw.split(divider);
        };

        List<String> mainSinoVietnameseList = new ArrayList<>();
        for (String kanji : kanjiArray) {
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
            mainSinoVietnameseList.add(mainSinoVietnamese.toString().trim());
        }
        return String.join("\n", mainSinoVietnameseList);
    }

    @Override
    @Transactional
    public List<KanjiResponse> importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile) {
        Map<String, String> mainSinoVietnameseMap = mainSinoVietnameseXlsxImport.importMainSinoVietnamese(mainSinoVietnameseFile);
        List<KanjiEntity> kanjiEntities = mainSinoVietnameseXlsxImport.updateKanjiWithMainSinoVietnamese(mainSinoVietnameseMap);
        return kanjiEntities.stream().map(kanjiMapper::kanjiEntityToKanjiResponse).toList();
    }

    @Override
    @Transactional
    public List<KanjiResponse> importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles) {
        Map<String, List<SinoVietnameseEntry>> sinoVietnameseEntryMap = new HashMap<>();
        for (MultipartFile sinoVietnameseFile : sinoVietnameseFiles) {
            sinoVietnameseJsonImporter.importSinoVietnamese(sinoVietnameseFile, sinoVietnameseEntryMap);
        }
        List<KanjiEntity> kanjiEntities = sinoVietnameseJsonImporter.updateKanjiWithSinoVietnamese(sinoVietnameseEntryMap);
        return kanjiEntities.stream().map(kanjiMapper::kanjiEntityToKanjiResponse).toList();
    }
}
