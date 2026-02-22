"use client";
import React, { useState } from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Popover } from "@mui/material";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { useTranslations } from "next-intl";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { removeLessonFromFolder } from "@/services/folder.service";
import { useRouter } from "@/i18n/navigation";
import BasicButton from "@/components/ui/buttons/basic.button";
import RemoveIcon from "@mui/icons-material/Remove";

const FolderLessonMoreButton = ({
    lesson,
    folderId,
}: {
    lesson: LessonResponse;
    folderId: string | number;
}) => {
    const t = useTranslations();
    const { refresh } = useRouter();

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleRemoveFromFolder = async () => {
        await removeLessonFromFolder({
            lessonId: lesson.id,
            folderId: folderId,
        });
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
                    icon={<RemoveIcon />}
                    text={t("Pages.yourLibrary.lesson.removeFromFolder")}
                    textColor="text-tc-error"
                    onClick={handleRemoveFromFolder}
                    width={50}
                />
            </Popover>
        </>
    );
};

export default FolderLessonMoreButton;
