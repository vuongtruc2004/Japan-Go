import { KanjiTabs } from "@/types/app/i18n.type";

export interface IKanjiTab {
    readonly id: string;
    tabKeyTranslation: KanjiTabs;
    colorStyle: string;
}

export interface IGroupKanjiTab {
    readonly id: string;
    kanjiTabs: IKanjiTab[];
}