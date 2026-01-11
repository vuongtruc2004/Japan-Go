package com.nass.application_service.services.interfaces;

import com.nass.application_service.dto.response.KanjiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IKanjiService {

    List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile);

    KanjiResponse getKanjiById(Integer id);
}
