"use client";
import React, { useState } from "react";
import { Button, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import ExportIcon from "@/components/ui/icons/export.icon";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import CloseIcon from "@mui/icons-material/Close";
import SingleLesson from "@/features/your-library/components/lesson/single.lesson";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";

const LessonExportQuizletButton = ({
    lessons,
}: {
    lessons: LessonResponse[];
}) => {
    const t = useTranslations();
    const [open, setOpen] = useState(false);
    const [lessonIds, setLessonIds] = useState<number[]>([]);

    const handleAddLesson = (lessonId: number) => {
        setLessonIds((prevLessonIds) => [...prevLessonIds, lessonId]);
    };

    const handleRemoveLesson = (lessonId: number) => {
        setLessonIds((prevLessonIds) =>
            prevLessonIds.filter((id) => id !== lessonId),
        );
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

                    <div className="my-3 flex h-100 flex-col gap-y-1 overflow-y-auto pr-3">
                        {lessons.map((lesson) => {
                            return (
                                <SingleLesson
                                    moreButton={
                                        <Button
                                            onClick={() =>
                                                handleAddLesson(lesson.id)
                                            }
                                            variant="text"
                                            color="primary"
                                            sx={{
                                                aspectRatio: "1/1",
                                                borderRadius: "50%",
                                            }}
                                        >
                                            <AddCircleOutlineIcon />
                                        </Button>
                                    }
                                    lesson={lesson}
                                    key={lesson.id}
                                />
                            );
                        })}
                    </div>

                    <div className="mt-5 flex items-center justify-end gap-x-3">
                        <Button
                            onClick={() => setOpen(false)}
                            sx={{ columnGap: "8px" }}
                            variant="outlined"
                            color="error"
                        >
                            <CloseIcon fontSize="small" />
                            {t("Common.cancel")}
                        </Button>
                        <Button
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
