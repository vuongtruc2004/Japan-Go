import { ValidateField } from "@/types/share.type";

export interface IGetSinoVietnameseState {
    kanji: ValidateField;
    dividerType: "line" | "whitespace" | "custom";
    customValue: string;
    sinoVietnamese: string;
}