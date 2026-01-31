interface GrammarResponse extends BaseResponse<number> {
    grammarTitle: string;
    grammarMeaning: GrammarMeaningResponse;
    grammarStructure: GrammarStructureResponse;
    grammarExample: GrammarExampleResponse;
    grammarNote: GrammarNoteResponse;
}

interface GrammarExampleResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}

interface GrammarMeaningResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}

interface GrammarNoteResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}

interface GrammarStructureResponse extends BaseResponse<number> {
    sentences: SentenceResponse[];
}