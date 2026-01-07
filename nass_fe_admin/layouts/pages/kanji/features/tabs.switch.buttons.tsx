import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import { Dispatch, SetStateAction } from "react";
import { KANJI_TABS } from "../models/kanji.tabs";
import { IKanjiTab } from "../models/kanji.type";

const TabsSwitchButtons = ({
    activeTab,
    setActiveTab,
}: {
    activeTab: IKanjiTab;
    setActiveTab: Dispatch<SetStateAction<IKanjiTab>>;
}) => {
    const t = useTranslations("Pages.kanji.explore");

    const handleChangeTab = (newActiveTab: IKanjiTab) => {
        setActiveTab(newActiveTab);
    };

    return (
        <div className="flex items-center justify-between">
            <div className="flex items-center justify-start gap-x-3">
                {KANJI_TABS.map((tab) => {
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

            <Button sx={{ height: "36px" }} variant="outlined" color="success">
                <FileUploadOutlinedIcon fontSize="small" />
                <p className="ml-1.5 text-sm">{t("uploadFile")}</p>
            </Button>
        </div>
    );
};

export default TabsSwitchButtons;
