import { useTranslations } from "next-intl";
import { GROUP_KANJI_TAB } from "../constants/kanji.constants";
import { useActiveKanjiTab } from "../contexts/active.kanji.tab";
import { IKanjiTab } from "../types/kanji.ui.type";
import { getKanjiTabClassName } from "../utils/kanji.utils";

const TabsSwitchButtons = () => {
    const { activeTab, setActiveTab } = useActiveKanjiTab();
    const t = useTranslations();

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
                                const activeClass = getKanjiTabClassName(
                                    activeTab,
                                    tab,
                                );
                                return (
                                    <button
                                        key={tab.id}
                                        className={`${activeClass} bg-bgc-page flex h-10 w-max cursor-pointer items-center justify-center rounded-md border px-3 text-sm font-semibold transition-all duration-150`}
                                        onClick={() => handleChangeTab(tab)}
                                    >
                                        {t(
                                            `Common.jlptLevels.${tab.tabKeyTranslation}`,
                                        )}
                                    </button>
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
