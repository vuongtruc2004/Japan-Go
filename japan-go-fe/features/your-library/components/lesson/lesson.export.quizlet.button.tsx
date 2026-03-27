"use client";
import React, { useState } from "react";
import { Button, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import ExportIcon from "@/components/ui/icons/export.icon";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import CloseIcon from "@mui/icons-material/Close";
import LessonListToExport from "@/features/your-library/components/lesson/lesson.list.to.export";
import { exportGrammarLessonsToQuizletText } from "@/services/grammar.lesson.service";
import { useClipboard } from "@/hooks/use.clipboard";
import { toast } from "react-toastify";

const LessonExportQuizletButton = ({
    lessons,
}: {
    lessons: LessonResponse[];
}) => {
    const t = useTranslations();
    const [open, setOpen] = useState(false);
    const [lessonIds, setLessonIds] = useState<number[]>([]);
    const { copy } = useClipboard();

    const handleClose = () => {
        setOpen(false);
        setLessonIds([]);
    };

    const handleExportLessons = async () => {
        if (!lessonIds.length) return;

        const result = await exportGrammarLessonsToQuizletText({
            grammarLessonIds: lessonIds,
        });

        const isCopied = await copy(result);
        if (isCopied) {
            toast.success(t("Common.copied"), {
                hideProgressBar: true,
                closeOnClick: true,
                autoClose: 2000,
            });
            handleClose();
        }
    };

    return (
        <>
            <TooltipCustom
                title={t("Pages.yourLibrary.lesson.lessonExportQuizletButton")}
            >
                <Button
                    variant="outlined"
                    color="success"
                    sx={{ aspectRatio: "1/1" }}
                    onClick={() => setOpen(true)}
                >
                    <ExportIcon size={20} />
                </Button>
            </TooltipCustom>

            <Modal open={open}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 w-200 -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="font-semibold">
                        {t(
                            "Pages.yourLibrary.lesson.lessonExportQuizletButton",
                        )}
                    </h1>

                    <LessonListToExport
                        lessons={lessons}
                        setLessonIds={setLessonIds}
                        lessonIds={lessonIds}
                    />

                    <div className="mt-5 flex items-center justify-end gap-x-3">
                        <Button
                            onClick={handleClose}
                            sx={{ columnGap: "8px" }}
                            variant="outlined"
                            color="error"
                        >
                            <CloseIcon fontSize="small" />
                            {t("Common.cancel")}
                        </Button>
                        <Button
                            onClick={handleExportLessons}
                            type="submit"
                            variant="contained"
                            color="primary"
                        >
                            {t("Common.export")}
                        </Button>
                    </div>
                </div>
            </Modal>
        </>
    );
};

export default LessonExportQuizletButton;
