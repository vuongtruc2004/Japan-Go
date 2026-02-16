"use client";
import React from "react";
import { useTranslations } from "next-intl";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";
import Empty from "@/components/ui/empty";
import WrapBox from "@/components/ui/wrap.box";
import SingleLesson from "@/features/your-library/components/lesson/single.lesson";

const FolderLessonList = () => {
    const t = useTranslations();
    const { folder } = useFolderDetails();

    return (
        <WrapBox>
            <div className="flex flex-col gap-y-3">
                {folder.lessons.length === 0 ? (
                    <Empty text={t("Pages.yourLibrary.lesson.noLessons")} />
                ) : (
                    folder.lessons.map((lesson) => {
                        return <SingleLesson lesson={lesson} key={lesson.id} />;
                    })
                )}
            </div>
        </WrapBox>
    );
};

export default FolderLessonList;
