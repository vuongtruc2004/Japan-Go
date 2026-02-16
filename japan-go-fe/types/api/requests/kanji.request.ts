import { VocabularyRequest } from "@/types/api/requests/vocabulary.request";

export interface KanjiPageRequest {
    mainKanjiCharacter: string;
    vocabularies: VocabularyRequest[];
}
