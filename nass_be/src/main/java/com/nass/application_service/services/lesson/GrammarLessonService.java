package com.nass.application_service.services.lesson;

import com.nass.application_service.dto.responses.lesson.GrammarLessonResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.lesson.GrammarLessonMarkdownImporter;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.lesson.IGrammarLessonService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.lesson.GrammarLessonEntity;
import com.nass.infrastructure.repositories.lesson.GrammarLessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GrammarLessonService implements IGrammarLessonService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final GrammarLessonMarkdownImporter grammarLessonMarkdownImporter;
    private final GrammarLessonRepository grammarLessonRepository;
    private final ModelMapper modelMapper;

    /**
     * @param files list of markdown files by export from the notion (I use for noting grammars)
     * @return list of grammar after inserted to the database
     */
    @Transactional
    @Override
    public List<GrammarLessonResponse> importGrammarLessonsFromNotion(List<MultipartFile> files) {
        List<GrammarLessonEntity> grammarLessonEntities = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!fileValidator.isMarkdownFile(file)) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_NOT_MARKDOWN),
                        i18nService.translation(FileMessage.FILE_NOT_MARKDOWN)
                );
            }
            try (InputStream inputStream = file.getInputStream()) {
                String markdown = new String(inputStream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).trim();
                if (markdown.isBlank()) {
                    throw new FileNotValidException(
                            i18nService.translation(FileMessage.FILE_EMPTY),
                            i18nService.translation(FileMessage.FILE_EMPTY)
                    );
                }
                grammarLessonEntities.add(grammarLessonMarkdownImporter.importGrammarLessonFromNotion(markdown));
            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }
        List<GrammarLessonEntity> savedGrammarLessonEntities = grammarLessonRepository.saveAll(grammarLessonEntities);
        return savedGrammarLessonEntities.stream()
                .map(grammarLessonEntity -> modelMapper.map(grammarLessonEntity, GrammarLessonResponse.class))
                .toList();
    }
}
