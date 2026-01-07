package com.nass.application_service.services;

import com.nass.application_service.dto.entries.KanjiEntry;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.KanjiDicXmlImporter;
import com.nass.application_service.importers.SinoVietnameseJsonImporter;
import com.nass.application_service.services.interfaces.IKanjiService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KanjiService implements IKanjiService {
    private final FileValidator fileValidator;
    private final KanjiDicXmlImporter kanjiDicXmlImporter;
    private final SinoVietnameseJsonImporter sinoVietnameseJsonImporter;

    @Override
    public Map<String, KanjiEntry> getKanji(List<MultipartFile> sinovnFiles, MultipartFile kanjidic2File, MultipartFile kanjijlptFile) {
        if (!fileValidator.isXMLFile(kanjidic2File)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        Map<String, KanjiEntry> map = kanjiDicXmlImporter.getKanjiFromKANJIDIC2File(kanjidic2File, kanjijlptFile);
        for (MultipartFile sinovnFile : sinovnFiles) {
            sinoVietnameseJsonImporter.enrichSinoVietnameseToKanjiFromBank(map, sinovnFile);
        }
        return map;
    }
}
