import { BaseResponse } from "./base.response";

export interface SupportVocabularyResponse extends BaseResponse<number> {
    japanese: string;
    reading: string;
    meaning: string;
}

export interface VocabularyResponse extends BaseResponse<number> {
    japanese: string;
    sinoVietnamese: string;
    reading: string;
    meaning: string;
    note: string;
    kanjiVgList: string[];
    supportVocabularies: SupportVocabularyResponse[];
}
