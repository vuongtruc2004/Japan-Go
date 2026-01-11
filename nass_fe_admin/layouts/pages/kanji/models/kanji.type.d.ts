import { KanjiTabs } from "@/global";
interface IKanjiTab {
    readonly id: string;
    tabKeyTranslation: KanjiTabs;
    colorStyle: string;
}

interface IGroupKanjiTab {
    readonly id: string;
    kanjiTabs: IKanjiTab[];
}

interface IGetSinoVn {
    kanji: ValidateFields;
    dividerType: "line" | "whitespace" | "custom";
    customValue: string;
    sinoVn: string;
}