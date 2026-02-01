interface KanjiResponse extends BaseResponse<number> {
    kanjiCharacter: string;
    unicode: string;
    grade: number;
    strokeCount: number;
    frequency: number;
    jlptLevel: number;
    mainSinoVietnamese: string;
    svg: string;
    onyomiList: string[];
    kunyomiList: string[];
}

interface KanjiPageResponse extends BaseResponse<number> {
    mainKanji: KanjiResponse;
    vocabularies: VocabularyResponse[];
}