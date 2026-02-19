import { IKanjiTab } from "../types/kanji.ui.type";
import { DecoratorClassName } from "@/types/enums/share.enum";

export function getKanjiTabClassName(activeTab: IKanjiTab, tab: IKanjiTab) {
    return activeTab === tab ? tab.colorStyle : DecoratorClassName.PRIMARY;
}
