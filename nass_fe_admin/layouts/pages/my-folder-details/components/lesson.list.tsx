"use client";
import Empty from "@/components/common/empty";
import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";

const LessonList = () => {
    const { folder } = useFolderDetails();
    return (
        <div>
            {folder.lessons && folder.lessons.length > 0 ? (
                folder.lessons.map((lesson) => {
                    return <div key={lesson.id}></div>;
                })
            ) : (
                <Empty text="Không có bài giảng nào để hiển thị" />
            )}
        </div>
    );
};

export default LessonList;
