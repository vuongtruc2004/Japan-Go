package com.nass.application_service.services.interfaces;

import com.nass.application_service.dto.request.GetSinoVnRequest;
import com.nass.application_service.dto.response.KanjiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISinoVietnameseService {
    List<KanjiResponse> importSinoVietnamese(List<MultipartFile> sinoVietnameseFiles);

    List<String> getSinoVietnameseOfKanjiList(GetSinoVnRequest getSinoVnRequest);

    byte[] buildKanjiAndSinoVietnameseExcel();
}
