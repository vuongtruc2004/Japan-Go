import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import TranslateIcon from "@mui/icons-material/Translate";
import AbcIcon from "@mui/icons-material/Abc";

const SingleLessonToAdd = ({ lesson }: { lesson: LessonResponse }) => {
    return (
        <div>
            <span className="bg-bgc-page flex h-10 w-10 items-center justify-center rounded-md">
                {lesson.lessonType === "GRAMMAR" ? (
                    <AbcIcon fontSize="small" />
                ) : (
                    <TranslateIcon fontSize="small" />
                )}
            </span>
        </div>
    );
};

export default SingleLessonToAdd;
