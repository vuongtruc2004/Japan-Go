import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import { LessonType } from "@/types/enums/lesson.enum";

export interface FolderLessonRequest {
    folderId: number | string;
    lessonId: number | string;
}

export interface KanjiLessonRequest {
    lessonId?: number;
    folderId: number | null;
    lessonType: LessonType;
    lessonName: string;
    description: string;
    kanjiPages: KanjiPageRequest[];
}

export interface GrammarLessonRequest {
    folderId: number | string | null;
    files: File[];
}
