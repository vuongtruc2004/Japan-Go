import { BaseResponse } from "./base.response";
import { GrammarResponse } from "./grammar.response";
import { KanjiPageResponse } from "./kanji.response";
import { LessonType } from "@/types/enums/lesson.enum";

export interface LessonResponse extends BaseResponse<number> {
    lessonName: string;
    lessonType: LessonType;
    grammarLesson: GrammarLessonResponse;
    kanjiLesson: KanjiLessonResponse;
}

export interface GrammarLessonResponse extends BaseResponse<number> {
    grammarLessonTitle: string;
    grammars: GrammarResponse[];
}

export interface KanjiLessonResponse extends BaseResponse<number> {
    kanjiPages: KanjiPageResponse[];
}
