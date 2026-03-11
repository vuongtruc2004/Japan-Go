import { JlptLevels } from "@/types/app/i18n.type";

export interface IGrammarJlptLevel {
    readonly id: string;
    tabKeyTranslation: JlptLevels;
    jlptLevel: number;
    colorStyle: string;
}
