package com.japan_go_be.business.services.grammar;

import com.japan_go_be.business.dto.mappers.GrammarDtoMapper;
import com.japan_go_be.business.dto.requests.grammar.GrammarSearchRequest;
import com.japan_go_be.business.dto.responses.base.PageDetailsResponse;
import com.japan_go_be.business.dto.responses.grammar.GrammarResponse;
import com.japan_go_be.business.exception.FileNotValidException;
import com.japan_go_be.business.exception.NotFoundException;
import com.japan_go_be.business.i18n.I18nService;
import com.japan_go_be.business.specifications.grammar.GrammarSpecification;
import com.japan_go_be.contract.constants.message.FileMessage;
import com.japan_go_be.contract.constants.message.grammar.GrammarMessage;
import com.japan_go_be.infrastructure.entities.grammar.GrammarEntity;
import com.japan_go_be.infrastructure.repositories.grammar.GrammarRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarService {

    private final GrammarRepository grammarRepository;
    private final I18nService i18nService;
    private final GrammarDtoMapper grammarDtoMapper;
    private final GrammarSpecification grammarSpecification;

    public byte[] exportAllGrammarsInFolder(Long folderId) {
        List<GrammarEntity> grammars = grammarRepository.findAllByGrammarsInFolder(folderId);
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("grammars");
            int rowNum = 0;
            for (GrammarEntity grammar : grammars) {
                Row row = sheet.createRow(rowNum++);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                    i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
            );
        }
    }

    public PageDetailsResponse<List<GrammarResponse>> getAllGrammars(
            GrammarSearchRequest searchRequest,
            Pageable pageable
    ) {
        Specification<GrammarEntity> specification = Specification.where(grammarSpecification.hasSearchKey(searchRequest.searchKey()));

        Page<GrammarEntity> page = grammarRepository.findAll(specification, pageable);

        List<GrammarResponse> grammarResponseList = page.getContent()
                .stream()
                .map(grammarDtoMapper::grammarEntityToGrammarResponseSummary)
                .toList();

        return PageDetailsResponse.<List<GrammarResponse>>builder()
                .currentPage(page.getNumber() + 1)
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(grammarResponseList)
                .build();
    }

    public GrammarResponse getGrammarDetailsById(Long id) {
        GrammarEntity grammarEntity = grammarRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        i18nService.translation(GrammarMessage.GRAMMAR_NOT_FOUND, id),
                        i18nService.translation(GrammarMessage.GRAMMAR_NOT_FOUND, id)
                ));
        return grammarDtoMapper.grammarEntityToGrammarResponseDetails(grammarEntity);
    }
}
