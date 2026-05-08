package org.japan.service.grammar;

import lombok.RequiredArgsConstructor;
import org.japan.dto.mapper.GrammarDtoMapper;
import org.japan.dto.request.grammar.GrammarSearchRequest;
import org.japan.dto.response.base.PageDetailsResponse;
import org.japan.dto.response.grammar.GrammarResponse;
import org.japan.entity.grammar.GrammarEntity;
import org.japan.exception.NotFoundException;
import org.japan.i18n.I18nService;
import org.japan.message.grammar.GrammarMessage;
import org.japan.persistence.repository.grammar.GrammarRepository;
import org.japan.specification.grammar.GrammarSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarService {

    private final GrammarRepository grammarRepository;
    private final I18nService i18nService;
    private final GrammarDtoMapper grammarDtoMapper;
    private final GrammarSpecification grammarSpecification;

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
