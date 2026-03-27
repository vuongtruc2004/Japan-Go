import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import Empty from "@/components/ui/empty";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import SingleLesson from "@/features/your-library/components/lesson/single.lesson";
import LessonMoreButton from "@/features/your-library/components/lesson/lesson.more.button";
import LessonDeleteAllButton from "@/features/your-library/components/lesson/lesson.delete.all.button";
import LessonExportQuizletButton from "@/features/your-library/components/lesson/lesson.export.quizlet.button";

const LessonList = ({ lessons }: { lessons: LessonResponse[] }) => {
    const t = useTranslations("Pages.yourLibrary");
    if (!lessons.length) {
        return (
            <div className="flex flex-col items-center">
                <Empty text={t("lesson.noLessons")} />
                <Button variant="contained" color="primary">
                    {t("lesson.createLesson")}
                </Button>
            </div>
        );
    }
    return (
        <>
            <div className="mb-3 flex items-center justify-between">
                <h1 className="font-semibold">{t("lesson.lessonList")}</h1>
                <div className="flex items-center gap-x-3">
                    <LessonExportQuizletButton lessons={lessons} />
                    <LessonDeleteAllButton />
                </div>
            </div>
            <div className="flex flex-col gap-y-1">
                {lessons.map((lesson) => {
                    return (
                        <SingleLesson
                            lesson={lesson}
                            key={lesson.id}
                            moreButton={<LessonMoreButton lesson={lesson} />}
                        />
                    );
                })}
            </div>
        </>
    );
};

export default LessonList;
