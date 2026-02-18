import { BaseResponse } from "./base.response";

export interface VocabularyResponse extends BaseResponse<number> {
    japanese: string;
    sinoVietnamese: string;
    reading: string;
    meaning: string;
    note: string;
}
