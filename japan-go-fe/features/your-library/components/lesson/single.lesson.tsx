import React from "react";
import AbcIcon from "@mui/icons-material/Abc";
import TranslateIcon from "@mui/icons-material/Translate";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { useTranslations } from "next-intl";
import { Link } from "@/i18n/navigation";
import LessonMoreButton from "@/features/your-library/components/lesson/lesson.more.button";
import { slugifyText } from "@/utils/slugify.text";

const SingleLesson = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();
    return (
        <div className="hover:bg-bgc-page flex cursor-pointer items-center justify-between rounded-md px-4 py-2 transition-all duration-150">
            <Link
                href={{
                    pathname: "/lesson/kanji/[slug]",
                    params: {
                        slug: slugifyText(lesson.lessonName + "-" + lesson.id),
                    },
                }}
                className="flex w-full flex-1 items-center gap-x-3 pr-4"
            >
                <span className="bg-bgc-page text-tc-highlight flex h-10 w-10 items-center justify-center rounded-md">
                    {lesson.lessonType === "GRAMMAR" ? (
                        <AbcIcon fontSize="small" />
                    ) : (
                        <TranslateIcon fontSize="small" />
                    )}
                </span>
                <div className="text-sm font-semibold">
                    <p>{lesson.lessonName}</p>
                    <div className="text-tc-muted flex items-center gap-x-1">
                        <p>
                            {lesson.lessonType === "GRAMMAR"
                                ? t("Pages.yourLibrary.lesson.grammarLesson")
                                : t("Pages.yourLibrary.lesson.kanjiLesson")}
                        </p>
                        ・
                        <p>
                            {lesson.kanjiLesson?.kanjiPages.length ?? 34}{" "}
                            {lesson.lessonType === "GRAMMAR"
                                ? t("Common.grammar")
                                : t("Common.kanjiPage")}
                        </p>
                        ・
                        <p>
                            {t("Common.author")}: {"Truc"}
                        </p>
                    </div>
                </div>
            </Link>
            <LessonMoreButton lesson={lesson} />
        </div>
    );
};

export default SingleLesson;
