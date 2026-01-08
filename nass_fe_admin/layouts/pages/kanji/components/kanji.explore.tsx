"use client";
import AutoAwesomeIcon from "@mui/icons-material/AutoAwesome";
import { useTranslations } from "next-intl";
import KanjiToolsBox from "./kanji.tools.box";
import SingleKanjiTab from "./single.kanji.tab";

const KanjiExplore = () => {
    const t = useTranslations("Pages.kanji.explore");

    return (
        <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
            <h1 className="text-tc-primary mb-3 font-semibold">{t("title")}</h1>
            <KanjiToolsBox />
            <p className="text-tc-primary my-3 flex items-center gap-x-1.5 text-sm">
                <AutoAwesomeIcon fontSize="small" color="success" />
                <span>{t("suggestTitle")}</span>
            </p>
            <SingleKanjiTab />
        </div>
    );
};

export default KanjiExplore;
