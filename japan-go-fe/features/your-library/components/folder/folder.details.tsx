import React from "react";
import FolderDetailsHeader from "@/features/your-library/components/folder/folder.details.header";
import FolderLessonList from "@/features/your-library/components/folder/folder.lesson.list";

const FolderDetails = () => {
    return (
        <div className="flex flex-col gap-y-5">
            <FolderDetailsHeader />
            <FolderLessonList />
        </div>
    );
};

export default FolderDetails;
