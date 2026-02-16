"use client";
import React from "react";
import { useTranslations } from "next-intl";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";
import Empty from "@/components/ui/empty";
import WrapBox from "@/components/ui/wrap.box";

const FolderLessonList = () => {
    const t = useTranslations();
    const { folder } = useFolderDetails();

    return (
        <WrapBox>
            {folder.lessons.length === 0 ? (
                <Empty text={t("Pages.yourLibrary.lesson.noLessons")} />
            ) : (
                folder.lessons.map((lesson) => {
                    return <div key={lesson.id}>{lesson.lessonName}</div>;
                })
            )}
        </WrapBox>
    );
};

export default FolderLessonList;
