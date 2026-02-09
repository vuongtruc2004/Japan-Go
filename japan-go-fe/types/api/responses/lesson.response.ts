import { BaseResponse } from "./base.response";
import { GrammarResponse } from "./grammar.response";
import { KanjiPageResponse } from "./kanji.response";

export interface LessonResponse extends BaseResponse<number> {
    lessonName: string;
    lessonType: "KANJI" | "GRAMMAR";
}

export interface LessonDetailsResponse extends LessonResponse {
    grammarLesson: GrammarLessonResponse;
    kanjiLesson: KanjiLessonResponse;
}

export interface GrammarLessonResponse extends BaseResponse<number> {
    grammarLessonTitle: string;
}

export interface GrammarLessonDetailsResponse extends GrammarLessonResponse {
    grammars: GrammarResponse[];
}

export interface KanjiLessonResponse extends BaseResponse<number> {
    kanjiPages: KanjiPageResponse[];
}
