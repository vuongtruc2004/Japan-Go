import { ValidateField } from "@/types/share.type";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";

export interface IImportKanjiDataState {
    kanjiData: ValidateField;
    kanjiPages: KanjiPageRequest[];
    success: boolean;
}
