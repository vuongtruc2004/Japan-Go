"use client";
import React, { useState } from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Button, Popover } from "@mui/material";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { useTranslations } from "next-intl";
import RemoveIcon from "@mui/icons-material/Remove";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { removeLessonFromFolder } from "@/services/folder.service";
import { useRouter } from "@/i18n/navigation";

const LessonMoreButton = ({
    lesson,
    folderId,
}: {
    lesson: LessonResponse;
    folderId: string | number | null;
}) => {
    const t = useTranslations();
    const { refresh } = useRouter();

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleRemoveFromFolder = async () => {
        if (!folderId) return;
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
                <div>
                    {folderId && (
                        <Button
                            variant="text"
                            color="error"
                            onClick={handleRemoveFromFolder}
                        >
                            <RemoveIcon fontSize="small" sx={{ mr: "4px" }} />
                            {t("Pages.yourLibrary.lesson.removeFromFolder")}
                        </Button>
                    )}
                </div>
            </Popover>
        </>
    );
};

export default LessonMoreButton;
