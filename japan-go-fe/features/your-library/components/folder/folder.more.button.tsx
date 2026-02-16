import React from "react";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { Button } from "@mui/material";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useTranslations } from "next-intl";

const FolderMoreButton = () => {
    const t = useTranslations();
    return (
        <div>
            <TooltipCustom title={t("Common.viewMore")}>
                <Button
                    variant="text"
                    color="primary"
                    sx={{
                        minWidth: "40px",
                        width: "40px",
                        borderRadius: "50%",
                    }}
                >
                    <MoreHorizOutlinedIcon />
                </Button>
            </TooltipCustom>
        </div>
    );
};

export default FolderMoreButton;
