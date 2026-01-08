package com.nass.application_service.services.interfaces;

import com.nass.application_service.dto.request.GetSinoVnRequest;
import com.nass.application_service.dto.response.JlptGroupKanjiResponse;
import com.nass.application_service.dto.response.KanjiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IKanjiService {

    List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile);

    List<KanjiResponse> enrichSinoVietnameseToAllKanji(List<MultipartFile> sinoVietnameseFiles);

    KanjiResponse getKanjiById(Integer id);

    List<JlptGroupKanjiResponse> getAllJlptGroupKanji();

    List<String> getSinoVietnameseOfKanjiList(GetSinoVnRequest getSinoVnRequest);
}
