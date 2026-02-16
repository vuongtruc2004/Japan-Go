"use client";
import AutoAwesomeIcon from "@mui/icons-material/AutoAwesome";
import { useTranslations } from "next-intl";
import KanjiToolsBox from "./kanji.tools.box";
import SingleKanjiTab from "./single.kanji.tab";
import WrapBox from "@/components/ui/wrap.box";

const KanjiExplore = () => {
    const t = useTranslations("Pages.kanji.explore");

    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">{t("title")}</h1>
            <KanjiToolsBox />
            <p className="my-3 flex items-center gap-x-1.5 text-sm">
                <AutoAwesomeIcon fontSize="small" color="success" />
                <span>{t("suggestTitle")}</span>
            </p>
            <SingleKanjiTab />
        </WrapBox>
    );
};

export default KanjiExplore;
