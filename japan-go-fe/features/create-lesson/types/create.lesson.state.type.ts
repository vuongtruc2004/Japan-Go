import { ValidateField } from "@/types/app/share.type";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import { LessonResponse } from "@/types/api/responses/lesson.response";

export interface IImportKanjiDataState {
    kanjiData: ValidateField;
    kanjiPages: KanjiPageRequest[];
    success: boolean;
}

export interface ICreateKanjiLessonState {
    lessonName: ValidateField;
    description: ValidateField;
    success: boolean;
    lesson?: LessonResponse;
}
