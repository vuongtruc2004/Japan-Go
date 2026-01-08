package com.nass.application_service.services;

import com.nass.application_service.dto.mapper.KanjiMapper;
import com.nass.application_service.dto.response.JlptGroupKanjiResponse;
import com.nass.application_service.dto.response.KanjiResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.exceptions.NotFoundException;
import com.nass.application_service.importers.KanjiDicXmlImporter;
import com.nass.application_service.importers.SinoVietnameseJsonImporter;
import com.nass.application_service.services.interfaces.IKanjiService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.FileMessage;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import com.nass.infrastructure.repositories.KanjiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanjiService implements IKanjiService {
    private final FileValidator fileValidator;
    private final KanjiDicXmlImporter kanjiDicXmlImporter;
    private final SinoVietnameseJsonImporter sinoVietnameseJsonImporter;
    private final KanjiMapper kanjiMapper;
    private final KanjiRepository kanjiRepository;

    @Override
    public List<String> getSinoVietnameseOfKanjiList(String kanjiRaw, String divider) {
        List<String> list = new ArrayList<>();
        String[] kanjiList = kanjiRaw.split(divider);
        return list;
    }

    @Override
    public List<JlptGroupKanjiResponse> getAllJlptGroupKanji() {
        return List.of();
    }

    @Override
    public KanjiResponse getKanjiById(Integer id) {
        KanjiEntity kanjiEntity = kanjiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kanji not found!"));
        return kanjiMapper.kanjiEntityToKanjiResponse(kanjiEntity);
    }

    @Override
    @Transactional
    public List<KanjiResponse> enrichSinoVietnameseToAllKanji(List<MultipartFile> sinoVietnameseFiles) {
        List<KanjiEntity> list = new ArrayList<>();
        for (MultipartFile sinoVietnameseFile : sinoVietnameseFiles) {
            sinoVietnameseJsonImporter.enrichSinoVietnameseToAllKanji(sinoVietnameseFile, list);
        }
        List<KanjiEntity> savedList = kanjiRepository.saveAll(list);
        return savedList.stream().map(kanjiMapper::kanjiEntityToKanjiResponse).toList();
    }

    @Override
    @Transactional
    public List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile) {
        if (!fileValidator.isXMLFile(kanjidicFile)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        if (!fileValidator.isJSONFile(kanjijlptFile)) {
            throw new FileNotValidException(
                    FileMessage.IS_NOT_XML_FILE_DEV,
                    FileMessage.IS_NOT_XML_FILE_USER
            );
        }
        List<KanjiEntity> list = kanjiDicXmlImporter.importKanjiFromKanjidic(kanjidicFile, kanjijlptFile);
        List<KanjiEntity> savedList = kanjiRepository.saveAll(list);
        return savedList.stream().map(kanjiMapper::kanjiEntityToKanjiResponse).toList();
    }
}
