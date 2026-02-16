import { ValidateField } from "@/types/app/share.type";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import { ApiResponse } from "@/types/api/responses/base.response";
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
    apiResponse?: ApiResponse<LessonResponse>;
}
