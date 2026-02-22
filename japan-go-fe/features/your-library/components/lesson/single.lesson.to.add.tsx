import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import TranslateIcon from "@mui/icons-material/Translate";
import { useTranslations } from "next-intl";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";
import { Button } from "@mui/material";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import LibraryBooksOutlinedIcon from "@mui/icons-material/LibraryBooksOutlined";
import {
    addLessonToFolder,
    removeLessonFromFolder,
} from "@/services/folder.service";
import { useRouter } from "@/i18n/navigation";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import { folderHasLesson } from "@/features/your-library/utils/your.library.utils";

const SingleLessonToAdd = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();
    const { folder } = useFolderDetails();
    const { refresh } = useRouter();

    const handleAddAndRemoveLessonFromFolder = async () => {
        if (folderHasLesson(folder, lesson.id)) {
            await removeLessonFromFolder({
                lessonId: lesson.id,
                folderId: folder.id,
            });
        } else {
            await addLessonToFolder({
                lessonId: lesson.id,
                folderId: folder.id,
            });
        }
        refresh();
    };

    return (
        <div
            className="hover:bg-bgc-page flex cursor-pointer items-center justify-between rounded-md p-2 transition-all duration-150"
            onClick={handleAddAndRemoveLessonFromFolder}
        >
            <div className="flex w-full flex-1 items-center gap-x-3 pr-4">
                <span className="bg-bgc-page text-tc-highlight flex h-10 w-10 items-center justify-center rounded-md">
                    {lesson.lessonType === "GRAMMAR" ? (
                        <LibraryBooksOutlinedIcon />
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
                            {lesson.pageCount}{" "}
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
            </div>

            {folder.lessons.find((l) => l.id === lesson.id) ? (
                <Button
                    variant="text"
                    color="primary"
                    sx={{
                        width: "40px",
                        minWidth: "40px",
                        borderRadius: "50%",
                    }}
                >
                    <CheckCircleIcon />
                </Button>
            ) : (
                <Button
                    variant="text"
                    color="primary"
                    sx={{
                        width: "40px",
                        minWidth: "40px",
                        borderRadius: "50%",
                    }}
                >
                    <AddCircleOutlineIcon />
                </Button>
            )}
        </div>
    );
};

export default SingleLessonToAdd;
