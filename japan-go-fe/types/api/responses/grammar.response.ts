import { BaseResponse } from "./base.response";
import { SentenceResponse } from "./common.response";

export interface GrammarResponse extends BaseResponse<number> {
    grammarTitle: string;
    grammarTitleFurigana: string;
    grammarTitleRomaji: string;
    translation: string;
    jlptLevel: number;
    s;
    grammarMeaning: GrammarMeaningResponse;
    grammarStructure: GrammarStructureResponse;
    grammarExample: GrammarExampleResponse;
    grammarNote: GrammarNoteResponse;
}

export interface GrammarExampleResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}

export interface GrammarMeaningResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}

export interface GrammarNoteResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}

export interface GrammarStructureResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}
