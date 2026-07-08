package org.japan.service.lesson;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.japan.constants.lesson.LessonTypeEnum;
import org.japan.dto.request.common.FolderLessonRequest;
import org.japan.dto.request.lesson.KanjiLessonImportRequest;
import org.japan.dto.response.lesson.LessonResponse;
import org.japan.entity.kanji.KanjiEntity;
import org.japan.entity.kanji.KanjiPageEntity;
import org.japan.entity.lesson.BookEntity;
import org.japan.entity.lesson.KanjiLessonEntity;
import org.japan.entity.lesson.LessonEntity;
import org.japan.entity.vocabulary.SupportVocabularyEntity;
import org.japan.entity.vocabulary.VocabularyEntity;
import org.japan.exception.FileNotValidException;
import org.japan.exception.kanji.KanjiException;
import org.japan.helper.lesson.BookHelper;
import org.japan.i18n.I18nService;
import org.japan.importer.kanji.KanjiPageXlsxImporter;
import org.japan.mapper.lesson.LessonMapper;
import org.japan.message.FileMessage;
import org.japan.message.kanji.KanjiMessage;
import org.japan.persistence.repository.kanji.KanjiRepository;
import org.japan.persistence.repository.lesson.LessonRepository;
import org.japan.service.common.FolderService;
import org.japan.validator.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KanjiLessonService {
    private final FileValidator fileValidator;
    private final I18nService i18nService;
    private final KanjiPageXlsxImporter kanjiPageXlsxImporter;
    private final LessonRepository lessonRepository;
    private final KanjiRepository kanjiRepository;
    private final LessonMapper lessonMapper;
    private final FolderService folderService;
    private final BookHelper bookHelper;
    private static final String KANJI_PAGE_SHEET_NAME = "Import";

    @Transactional
    public LessonResponse importKanjiLessonFromExcel(KanjiLessonImportRequest request) {
        MultipartFile file = request.file();
        if (!fileValidator.isExcelFile(file)) {
            throw new FileNotValidException(
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL),
                    i18nService.translation(FileMessage.FILE_NOT_EXCEL)
            );
        }

        BookEntity book = bookHelper.getBookById(request.bookId());

        LessonEntity lesson = LessonEntity.builder()
                .lessonName(request.lessonName())
                .description(request.description())
                .lessonType(request.lessonType())
                .book(book)
                .build();

        if (lesson.getLessonType().equals(LessonTypeEnum.KANJI)) {
            KanjiLessonEntity kanjiLesson = new KanjiLessonEntity();
            try (
                    InputStream inputStream = file.getInputStream();
                    Workbook workbook = new XSSFWorkbook(inputStream)
            ) {

                Sheet sheet = workbook.getSheet(KANJI_PAGE_SHEET_NAME);
                int lastRowNum = sheet.getLastRowNum();

                Map<String, KanjiPageEntity> kanjiPageMap = new LinkedHashMap<>();
                VocabularyEntity vocabulary = null;

                for (int rowNum = 0; rowNum <= lastRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    Cell kanjiCell1 = row.getCell(1);

                    if (kanjiCell1 == null) {
                        continue;
                    }

                    String japanese1 = kanjiCell1.getStringCellValue().trim();

                    if (!japanese1.isEmpty()) {
                        vocabulary = new VocabularyEntity();
                        vocabulary.setJapanese(japanese1);
                        vocabulary.setReading(row.getCell(2).getStringCellValue().trim());
                        vocabulary.setMeaning(row.getCell(3).getStringCellValue().trim());
                        vocabulary.setNote(row.getCell(4).getStringCellValue().trim());
                    }

                    if (vocabulary == null) {
                        throw new FileNotValidException(
                                i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, rowNum),
                                i18nService.translation(FileMessage.FILE_ERROR_AT_LINE, rowNum)
                        );
                    }

                    Cell mainKanjiCell = row.getCell(0);

                    if (mainKanjiCell != null) {
                        String mainKanji = mainKanjiCell.getStringCellValue().trim();
                        if (!mainKanji.isEmpty()) {
                            KanjiPageEntity kanjiPageEntity = kanjiPageMap.get(mainKanji);
                            if (kanjiPageEntity == null) {
                                kanjiPageEntity = new KanjiPageEntity();

                                KanjiEntity mainKanjiEntity = kanjiRepository.findByKanjiCharacter(mainKanji)
                                        .orElseThrow(() -> new KanjiException(
                                                i18nService.translation(KanjiMessage.KANJI_CHARACTER_NOT_FOUND, mainKanji),
                                                i18nService.translation(KanjiMessage.KANJI_CHARACTER_NOT_FOUND, mainKanji)
                                        ));

                                kanjiPageEntity.setMainKanji(mainKanjiEntity);
                                kanjiPageEntity.setKanjiLesson(kanjiLesson);

                                kanjiPageMap.put(mainKanji, kanjiPageEntity);
                            }
                            kanjiPageEntity.getVocabularies().add(vocabulary);

                            vocabulary.setKanjiPage(kanjiPageEntity);
                        }
                    }

                    Cell kanjiCell2 = row.getCell(5);

                    if (kanjiCell2 != null) {
                        SupportVocabularyEntity supportVocabulary = new SupportVocabularyEntity();
                        String japanese2 = kanjiCell2.getStringCellValue().trim();

                        if (!japanese2.isEmpty()) {
                            supportVocabulary.setJapanese(japanese2);
                            supportVocabulary.setReading(row.getCell(6).getStringCellValue().trim());
                            supportVocabulary.setMeaning(row.getCell(7).getStringCellValue().trim());
                            supportVocabulary.setVocabulary(vocabulary);

                            vocabulary.getSupportVocabularies().add(supportVocabulary);
                        }
                    }
                }

                kanjiLesson.setKanjiPages(kanjiPageMap.values().stream().toList());
                lesson.setKanjiLesson(kanjiLesson);

            } catch (Exception e) {
                throw new FileNotValidException(
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage()),
                        i18nService.translation(FileMessage.FILE_ERROR, e.getMessage())
                );
            }
        }

        LessonEntity savedLesson = lessonRepository.save(lesson);
        if (request.folderId() != null) {
            folderService.addLessonToFolder(new FolderLessonRequest(
                    request.folderId(),
                    savedLesson.getId())
            );
        }

        return lessonMapper.mapEntityToResponseSummary(savedLesson);
    }
}
