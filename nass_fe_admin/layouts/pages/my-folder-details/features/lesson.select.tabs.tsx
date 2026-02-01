"use client";
import Empty from "@/components/common/empty";
import { getAllLessons } from "@/libs/api/lesson/lesson";
import { useEffect, useState } from "react";
import SingleLesson from "../components/single.lesson";
import { LESSON_LIST_SELECT_TABS } from "../models/lesson.list.select.tabs";

const LessonSelectTabs = () => {
    const [activeTab, setActiveTab] = useState<ILessonSelectTab>(
        LESSON_LIST_SELECT_TABS[0],
    );
    const [lessons, setLessons] = useState<LessonResponse[]>([]);

    const handleChangeTab = (newActiveTab: ILessonSelectTab) => {
        setActiveTab(newActiveTab);
    };

    useEffect(() => {
        const getLessonList = async () => {
            if (activeTab.id === "tab-1") {
                setLessons([]);
            } else {
                const page = await getAllLessons();
                setLessons(page.content);
            }
        };
        getLessonList();
    }, [activeTab]);

    return (
        <div>
            <div className="flex items-center gap-x-3">
                {LESSON_LIST_SELECT_TABS.map((tab) => {
                    const activeClass =
                        activeTab === tab
                            ? "text-green-700 dark:text-green-500 bg-green-500/10 border-green-500/20"
                            : "hover:bg-hbgc-highlight transition-all duration-150 border-bdc-primary hover:border-bdc-primary";
                    return (
                        <div
                            key={tab.id}
                            className={`${activeClass} bg-bgc-page flex h-9 w-max cursor-pointer items-center justify-center rounded-md border px-3 text-sm font-semibold`}
                            onClick={() => handleChangeTab(tab)}
                        >
                            {tab.tabKeyTranslation}
                        </div>
                    );
                })}
            </div>
            <div>
                {lessons && lessons.length > 0 ? (
                    lessons.map((lesson) => {
                        return <SingleLesson key={lesson.id} lesson={lesson} />;
                    })
                ) : (
                    <Empty text="Không có bài giảng nào để hiển thị" />
                )}
            </div>
        </div>
    );
};

export default LessonSelectTabs;
