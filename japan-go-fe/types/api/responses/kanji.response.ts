import { BaseResponse } from "./base.response";
import { VocabularyResponse } from "./vocabulary.response";

export interface KanjiResponse extends BaseResponse<number> {
    kanjiCharacter: string;
    unicode: string;
    kanjiVg: string;
    grade: number;
    strokeCount: number;
    frequency: number;
    jlptLevel: number;
    mainSinoVietnamese: string;
    onyomiList: string[];
    kunyomiList: string[];
}

export interface KanjiPageResponse extends BaseResponse<number> {
    mainKanji: KanjiResponse;
    vocabularies: VocabularyResponse[];
}
