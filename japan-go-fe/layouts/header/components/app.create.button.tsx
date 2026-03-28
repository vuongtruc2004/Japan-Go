import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import React, { useState } from "react";
import AppCreatePopover from "@/components/ui/popovers/app.create.popover";

const AppCreateButton = () => {
    const t = useTranslations();
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    return (
        <>
            <TooltipCustom arrow title={t("Common.create")}>
                <Button
                    onClick={handleClick}
                    variant="text"
                    color="success"
                    sx={{
                        width: "40px",
                        minWidth: "40px",
                        height: "40px",
                        borderRadius: "50%",
                    }}
                >
                    <AddCircleOutlineOutlinedIcon />
                </Button>
            </TooltipCustom>

            <AppCreatePopover anchorEl={anchorEl} setAnchorEl={setAnchorEl} />
        </>
    );
};

export default AppCreateButton;
