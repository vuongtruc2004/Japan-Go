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

interface IGetSinoVnState {
    kanji: ValidateField;
    dividerType: "line" | "whitespace" | "custom";
    customValue: string;
    sinoVn: string;
}