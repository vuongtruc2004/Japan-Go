import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Button } from "@mui/material";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { useTranslations } from "next-intl";

const LessonMoreButton = ({ lesson }: { lesson: LessonResponse }) => {
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

export default LessonMoreButton;
