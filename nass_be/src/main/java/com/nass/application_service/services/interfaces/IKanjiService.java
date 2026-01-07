package com.nass.application_service.services.interfaces;

import com.nass.application_service.dto.entries.KanjiEntry;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IKanjiService {

    Map<String, KanjiEntry> getKanji(List<MultipartFile> sinovnFiles, MultipartFile kanjidic2File, MultipartFile kanjijlptFile);

}
