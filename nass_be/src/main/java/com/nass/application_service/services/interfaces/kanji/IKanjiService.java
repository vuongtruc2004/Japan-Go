package com.nass.application_service.services.interfaces.kanji;

import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface IKanjiService {
    List<KanjiEntity> importKanjiFromKanjidicIS(InputStream kanjidicInputstream, InputStream kanjiJlptInputstream);

    List<KanjiResponse> importKanjiFromKanjidicMF(MultipartFile kanjidicFile, MultipartFile kanjiJlptFile);
}
