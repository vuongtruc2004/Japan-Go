"use client";
import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Button, Popover } from "@mui/material";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { useTranslations } from "next-intl";
import RemoveIcon from "@mui/icons-material/Remove";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";

const LessonMoreButton = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();

    const [anchorEl, setAnchorEl] = React.useState<HTMLButtonElement | null>(
        null,
    );

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <TooltipCustom title={t("Common.viewMore")}>
                <IconButtonCustom onClick={handleClick}>
                    <MoreHorizOutlinedIcon />
                </IconButtonCustom>
            </TooltipCustom>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <div>
                    <Button variant="text" color="error">
                        <RemoveIcon fontSize="small" sx={{ mr: "4px" }} />
                        {t("Pages.yourLibrary.lesson.removeFromFolder")}
                    </Button>
                </div>
            </Popover>
        </div>
    );
};

export default LessonMoreButton;
