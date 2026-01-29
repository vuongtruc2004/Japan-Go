package com.nass.application_service.services.interfaces.kanji;

import com.nass.application_service.dtos.requests.kanji.GetSinoVietnameseRequest;
import com.nass.application_service.dtos.responses.kanji.KanjiResponse;
import com.nass.infrastructure.entities.kanji.KanjiEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface ISinoVietnameseService {
    List<KanjiEntity> importSinoVietnameseIS(List<InputStream> sinoVietnameseInputstreamList);

    List<KanjiEntity> importMainSinoVietnameseIS(InputStream mainSinoVietnameseInputstream);

    List<KanjiResponse> importSinoVietnameseMF(List<MultipartFile> sinoVietnameseFiles);

    List<KanjiResponse> importMainSinoVietnameseMF(MultipartFile mainSinoVietnameseFile);

    String getSinoVietnameseOfKanjiList(GetSinoVietnameseRequest getSinoVietnameseRequest);
}
