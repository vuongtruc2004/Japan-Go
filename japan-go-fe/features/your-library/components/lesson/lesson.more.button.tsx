import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Button, Popover } from "@mui/material";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { useTranslations } from "next-intl";
import RemoveIcon from "@mui/icons-material/Remove";

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
                <Button
                    onClick={handleClick}
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
                        <RemoveIcon fontSize="small" />
                        {t("Pages.yourLibrary.lesson.removeFromFolder")}
                    </Button>
                </div>
            </Popover>
        </div>
    );
};

export default LessonMoreButton;
