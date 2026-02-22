"use client";
import React, { useState } from "react";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { Popover } from "@mui/material";
import BasicButton from "@/components/ui/buttons/basic.button";
import { useTranslations } from "next-intl";
import { useRouter } from "@/i18n/navigation";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import { deleteLesson } from "@/services/lesson.service";

const LessonMoreButton = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();
    const { refresh } = useRouter();

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleDeleteLesson = async () => {
        await deleteLesson(lesson.id);
        refresh();
    };
    return (
        <>
            <TooltipCustom title={t("Common.viewMore")}>
                <IconButtonCustom
                    onClick={(event) => setAnchorEl(event.currentTarget)}
                >
                    <MoreHorizOutlinedIcon />
                </IconButtonCustom>
            </TooltipCustom>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={() => setAnchorEl(null)}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <BasicButton
                    icon={<DeleteOutlineOutlinedIcon />}
                    text={t("Pages.lesson.deleteLesson")}
                    textColor="text-tc-error"
                    onClick={handleDeleteLesson}
                    width={50}
                />
            </Popover>
        </>
    );
};

export default LessonMoreButton;
