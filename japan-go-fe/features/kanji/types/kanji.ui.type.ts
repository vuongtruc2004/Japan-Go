import { JlptLevels } from "@/types/app/i18n.type";

export interface IKanjiTab {
    readonly id: string;
    tabKeyTranslation: JlptLevels;
    colorStyle: string;
}

export interface IGroupKanjiTab {
    readonly id: string;
    kanjiTabs: IKanjiTab[];
}
