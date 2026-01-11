package com.nass.application_service.services.interfaces;

import com.nass.application_service.dto.request.GetSinoVietnameseRequest;
import com.nass.application_service.dto.response.KanjiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISinoVietnameseService {
    List<KanjiResponse> importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles);

    List<KanjiResponse> importMainSinoVietnamese(MultipartFile mainSinoVietnameseFile);

    String getSinoVietnameseOfKanji(GetSinoVietnameseRequest getSinoVietnameseRequest);
}
