import { IKanjiTab } from "../types/ui.type";

export function getKanjiTabClassName(activeTab: IKanjiTab, tab: IKanjiTab) {
  return activeTab === tab
    ? tab.colorStyle
    : "hover:bg-hbgc-highlight transition-all duration-150 border-bdc-primary hover:border-bdc-primary";
}
