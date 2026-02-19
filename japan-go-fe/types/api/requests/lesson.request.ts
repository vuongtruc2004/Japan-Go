import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import { LessonType } from "@/types/enums/lesson.enum";

export interface FolderLessonRequest {
    folderId: number | string;
    lessonId: number | string;
}

export interface LessonRequest {
    lessonId?: number;
    folderId: number | null;
    lessonType: LessonType;
    lessonName: string;
    description: string;
    grammarLesson?: GrammarLessonRequest;
    kanjiLesson?: KanjiLessonRequest;
}

export interface KanjiLessonRequest {
    kanjiPages: KanjiPageRequest[];
}

export interface GrammarLessonRequest {
    ok: string;
}
