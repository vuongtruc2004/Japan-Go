import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { Button } from "@mui/material";
import SingleLesson from "@/features/your-library/components/lesson/single.lesson";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";

const LessonListToExport = ({
    lessons,
    setLessonIds,
    lessonIds,
}: {
    lessons: LessonResponse[];
    setLessonIds: React.Dispatch<React.SetStateAction<number[]>>;
    lessonIds: number[];
}) => {
    const handleAddLesson = (lessonId: number) => {
        setLessonIds((prevLessonIds) => [...prevLessonIds, lessonId]);
    };

    const handleRemoveLesson = (lessonId: number) => {
        setLessonIds((prevLessonIds) =>
            prevLessonIds.filter((id) => id !== lessonId),
        );
    };

    return (
        <div className="my-3 flex h-100 flex-col gap-y-1 overflow-y-auto pr-3">
            {lessons.map((lesson) => {
                const isAdded = lessonIds.includes(lesson.id);

                return (
                    <SingleLesson
                        moreButton={
                            isAdded ? (
                                <Button
                                    onClick={() =>
                                        handleRemoveLesson(lesson.id)
                                    }
                                    variant="text"
                                    color="primary"
                                    sx={{
                                        aspectRatio: "1/1",
                                        borderRadius: "50%",
                                    }}
                                >
                                    <CheckCircleIcon />
                                </Button>
                            ) : (
                                <Button
                                    onClick={() => handleAddLesson(lesson.id)}
                                    variant="text"
                                    color="primary"
                                    sx={{
                                        aspectRatio: "1/1",
                                        borderRadius: "50%",
                                    }}
                                >
                                    <AddCircleOutlineIcon />
                                </Button>
                            )
                        }
                        lesson={lesson}
                        key={lesson.id}
                    />
                );
            })}
        </div>
    );
};

export default LessonListToExport;
