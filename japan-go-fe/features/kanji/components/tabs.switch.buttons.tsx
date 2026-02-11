import { useTranslations } from "next-intl";
import { GROUP_KANJI_TAB } from "../constants/constants";
import { useActiveKanjiTab } from "../contexts/active.kanji.tab";
import { IKanjiTab } from "../types/ui.type";
import { getKanjiTabClassName } from "../utils/utils";

const TabsSwitchButtons = () => {
    const { activeTab, setActiveTab } = useActiveKanjiTab();
    const t = useTranslations("Pages.kanji.explore");

    const handleChangeTab = (newActiveTab: IKanjiTab) => {
        setActiveTab(newActiveTab);
    };

    return (
        <div className="border-bdc-primary rounded-md border p-3">
            <div className="flex flex-col gap-y-3">
                {GROUP_KANJI_TAB.map((group) => {
                    return (
                        <div
                            className="flex items-center gap-x-3"
                            key={group.id}
                        >
                            {group.kanjiTabs.map((tab) => {
                                const activeClass = getKanjiTabClassName(activeTab, tab);
                                return (
                                    <div
                                        key={tab.id}
                                        className={`${activeClass} bg-bgc-page flex h-9 w-max cursor-pointer items-center justify-center rounded-md border px-3 text-sm font-semibold`}
                                        onClick={() => handleChangeTab(tab)}
                                    >
                                        {t(`levels.${tab.tabKeyTranslation}`)}
                                    </div>
                                );
                            })}
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default TabsSwitchButtons;

