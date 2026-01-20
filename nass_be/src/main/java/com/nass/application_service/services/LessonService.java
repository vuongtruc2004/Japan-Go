package com.nass.application_service.services;

import com.nass.application_service.dto.responses.grammar.LessonResponse;
import com.nass.application_service.exceptions.FileNotValidException;
import com.nass.application_service.importers.LessonMarkdownImporter;
import com.nass.application_service.services.interfaces.ILessonService;
import com.nass.application_service.validators.FileValidator;
import com.nass.infrastructure.entities.grammar.LessonEntity;
import com.nass.infrastructure.repositories.LessonRepository;
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
public class LessonService implements ILessonService {

    private final FileValidator fileValidator;
    private final LessonMarkdownImporter lessonMarkdownImporter;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<LessonResponse> importLessonsFromNotion(List<MultipartFile> files) {
        List<LessonEntity> lessons = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!fileValidator.isMarkdownFile(file)) {
                throw new FileNotValidException("Markdown file is not valid");
            }

            try (InputStream inputStream = file.getInputStream()) {
                String markdown = new String(inputStream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).trim();
                if (markdown.isEmpty()) {
                    throw new FileNotValidException("No markdown content");
                }
                lessons.add(lessonMarkdownImporter.importLessonFromNotion(markdown));
            } catch (Exception e) {
                throw new FileNotValidException(e.getMessage());
            }
        }
        List<LessonEntity> savedLessons = lessonRepository.saveAll(lessons);
        return savedLessons.stream().map(lesson -> modelMapper.map(lesson, LessonResponse.class)).toList();
    }
}
