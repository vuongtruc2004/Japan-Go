package com.nass.application_service.services.implementations.lesson;

import com.nass.application_service.dtos.responses.lesson.LessonResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.lesson.LessonXlsxImporter;
import com.nass.application_service.services.i18n.I18nService;
import com.nass.application_service.services.interfaces.lesson.ILessonService;
import com.nass.application_service.validators.FileValidator;
import com.nass.contract.constants.messages.common.FileMessage;
import com.nass.infrastructure.entities.lesson.LessonEntity;
import com.nass.infrastructure.repositories.lesson.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final LessonXlsxImporter lessonXlsxImporter;
    private final ModelMapper modelMapper;
    private final LessonRepository lessonRepository;

    /**
     * @param file an excel file that contains
     *             column 1: lesson name
     *             column 2: grammar lesson id
     * @return list of lesson response after inserted lesson to the database
     */
    @Transactional
    @Override
    public List<LessonResponse> importLessonsFromExcel(MultipartFile file) {
        if (!fileValidator.isExcelFile(file)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }
        List<LessonEntity> lessons = lessonXlsxImporter.importLessonsFromExcel(file);
        return lessons.stream()
                .map(lessonEntity -> modelMapper.map(lessonEntity, LessonResponse.class))
                .toList();
    }
}
