package com.nass.application_service.services.interfaces.kanji;

import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IKanjiService {

    List<KanjiResponse> importKanjiFromKanjidic(MultipartFile kanjidicFile, MultipartFile kanjijlptFile);
}
