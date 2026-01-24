package com.nass.application_service.services.interfaces.kanji;

import com.nass.application_service.dto.requests.kanji.GetSinoVietnameseRequest;
import com.nass.application_service.dto.responses.kanji.KanjiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISinoVietnameseService {
    List<KanjiResponse> importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles);

    List<KanjiResponse> importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile);

    String getSinoVietnameseOfKanji(GetSinoVietnameseRequest getSinoVietnameseRequest);
}
