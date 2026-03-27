"use client";
import React, { useState } from "react";
import { Button, Popover } from "@mui/material";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import { useTranslations } from "next-intl";
import WarningAmberIcon from "@mui/icons-material/WarningAmber";
import { useRouter } from "@/i18n/navigation";
import { deleteAllLessons } from "@/services/lesson.service";

const LessonDeleteAllButton = () => {
    const t = useTranslations();
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const { refresh } = useRouter();

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleDeleteAll = async () => {
        await deleteAllLessons();
        refresh();
    };

    return (
        <div>
            <Button
                variant="outlined"
                color="error"
                sx={{
                    aspectRatio: "1/1",
                }}
                onClick={(event) => setAnchorEl(event.currentTarget)}
            >
                <DeleteOutlineOutlinedIcon fontSize="small" />
            </Button>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "center",
                    horizontal: "left",
                }}
                transformOrigin={{
                    vertical: "center",
                    horizontal: "right",
                }}
            >
                <div className="w-62.5 px-4 py-2">
                    <div className="mb-3 flex items-start gap-x-3">
                        <WarningAmberIcon color="warning" />
                        <div className="text-sm font-semibold">
                            {t(
                                "Pages.yourLibrary.lesson.areYouSureToDeleteAllLessons",
                            )}
                        </div>
                    </div>
                    <div className="flex items-center justify-end gap-x-1.5">
                        <button
                            onClick={handleClose}
                            className="border-bdc-primary cursor-pointer rounded-sm border px-1.75 py-0.5 text-sm font-semibold"
                        >
                            {t("Common.cancel")}
                        </button>

                        <button
                            onClick={handleDeleteAll}
                            className="border-bdc-primary bg-bgc-error text-tc-pure cursor-pointer rounded-sm border px-1.75 py-0.5 text-sm font-semibold"
                        >
                            {t("Common.delete")}
                        </button>
                    </div>
                </div>
            </Popover>
        </div>
    );
};

export default LessonDeleteAllButton;
