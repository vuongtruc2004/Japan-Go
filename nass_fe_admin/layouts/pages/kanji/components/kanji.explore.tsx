"use client";
import AutoAwesomeIcon from "@mui/icons-material/AutoAwesome";
import { useTranslations } from "next-intl";
import { useState } from "react";
import TabsSwitchButtons from "../features/tabs.switch.buttons";
import { KANJI_TABS } from "../models/kanji.tabs";
import { IKanjiTab } from "../models/kanji.type";
import SingleKanjiTab from "./single.kanji.tab";

const KanjiExplore = () => {
    const t = useTranslations("Pages.kanji.explore");
    const [activeTab, setActiveTab] = useState<IKanjiTab>(KANJI_TABS[0]);

    return (
        <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
            <h1 className="text-tc-primary mb-3 font-semibold">{t("title")}</h1>
            <TabsSwitchButtons
                activeTab={activeTab}
                setActiveTab={setActiveTab}
            />
            <p className="text-tc-primary my-3 flex items-center gap-x-1.5 text-sm">
                <AutoAwesomeIcon fontSize="small" color="success" />
                <span>{t("suggestTitle")}</span>
            </p>
            <SingleKanjiTab activeTab={activeTab} />
        </div>
    );
};

export default KanjiExplore;
