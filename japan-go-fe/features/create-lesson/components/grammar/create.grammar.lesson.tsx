"use client";
import React from "react";
import WrapBox from "@/components/ui/wrap.box";
import { useTranslations } from "next-intl";
import CreateGrammarLessonForm from "@/features/create-lesson/components/grammar/create.grammar.lesson.form";
import { useGrammarLessonCreate } from "@/features/create-lesson/contexts/grammar.lesson.create.provider";
import Empty from "@/components/ui/empty";
import LibraryBooksOutlinedIcon from "@mui/icons-material/LibraryBooksOutlined";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { BookResponse } from "@/types/api/responses/lesson.response";

const CreateGrammarLesson = ({ books }: { books: BookResponse[] }) => {
    const t = useTranslations();
    const { files, setFiles } = useGrammarLessonCreate();

    const handelDeleteFile = (file: File) => {
        setFiles((prev) => prev.filter((f) => f !== file));
    };

    return (
        <div className="flex flex-col gap-y-5">
            <CreateGrammarLessonForm books={books} />

            <WrapBox>
                {files.length === 0 ? (
                    <Empty
                        text={t("Pages.createLesson.noGrammarDataToPreview")}
                    />
                ) : (
                    <div className="flex flex-col gap-y-3">
                        {files.map((file, index) => {
                            return (
                                <div
                                    key={file.name + "-" + index}
                                    className="hover:bg-bgc-page flex items-center justify-between rounded-md p-2 transition-all duration-150"
                                >
                                    <div className="flex cursor-pointer items-center gap-x-3">
                                        <span className="bg-bgc-page text-tc-highlight flex h-10 w-10 items-center justify-center rounded-md">
                                            <LibraryBooksOutlinedIcon fontSize="small" />
                                        </span>

                                        <p className="text-sm font-semibold">
                                            {file.name}
                                        </p>
                                    </div>

                                    <IconButtonCustom
                                        hasBgColor={false}
                                        onClick={() => handelDeleteFile(file)}
                                    >
                                        <DeleteOutlineOutlinedIcon color="error" />
                                    </IconButtonCustom>
                                </div>
                            );
                        })}
                    </div>
                )}
            </WrapBox>
        </div>
    );
};

export default CreateGrammarLesson;
