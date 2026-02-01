interface LessonResponse extends BaseResponse<number> {
    lessonName: string;
    lessonType: "KANJI" | "GRAMMAR";
}

interface LessonDetailsResponse extends LessonResponse {
    grammarLesson: GrammarLessonResponse;
    kanjiLesson: KanjiLessonResponse;
}

interface GrammarLessonResponse extends BaseResponse<number> {
    grammarLessonTitle: string;
}

interface GrammarLessonDetailsResponse extends GrammarLessonResponse {
    grammars: GrammarResponse[];
}

interface KanjiLessonResponse extends BaseResponse<number> {
    kanjiPages: KanjiPageResponse[];
}
