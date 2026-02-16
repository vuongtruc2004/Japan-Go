import { ValidateField } from "@/types/app/share.type";
import { DividerType } from "@/features/kanji/types/kanji.enum";

export interface IGetSinoVietnameseState {
    kanji: ValidateField;
    dividerType: DividerType;
    customValue: string;
    sinoVietnamese: string;
}
