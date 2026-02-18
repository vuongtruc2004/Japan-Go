import React from "react";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useTranslations } from "next-intl";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";

const FolderMoreButton = () => {
    const t = useTranslations();
    return (
        <TooltipCustom title={t("Common.viewMore")}>
            <IconButtonCustom>
                <MoreHorizOutlinedIcon />
            </IconButtonCustom>
        </TooltipCustom>
    );
};

export default FolderMoreButton;
