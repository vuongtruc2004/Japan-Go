import { useActiveKanjiTab } from "@/libs/wrapper/context/active-kanji-tab/active.kanji.tab.wrapper";
import { useTranslations } from "next-intl";
import { GROUP_KANJI_TAB } from "../models/kanji.tabs";
import { IKanjiTab } from "../models/kanji.type";

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
                                const activeClass =
                                    activeTab === tab
                                        ? `${tab.colorStyle}`
                                        : "hover:bg-hbgc-highlight transition-all duration-150 border-bdc-primary hover:border-bdc-primary";
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
