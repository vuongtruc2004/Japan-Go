"use client";
import React, { useState, useTransition } from "react";
import WrapBox from "@/components/ui/wrap.box";
import { Button } from "@mui/material";
import PublicOutlinedIcon from "@mui/icons-material/PublicOutlined";
import { useTranslations } from "next-intl";
import FilesUploadButton from "@/components/ui/buttons/files.upload.button";
import { useGrammarLessonCreate } from "@/features/create-lesson/contexts/grammar.lesson.create.provider";
import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import { useRouter } from "@/i18n/navigation";
import { useSearchParams } from "next/navigation";
import { parsePositiveInt } from "@/utils/parse.util";
import { createGrammarLessons } from "@/services/lesson.service";
import GrammarJlptLevelSelect from "@/features/create-lesson/components/grammar/grammar.jlpt.level.select";
import { GrammarLessonRequest } from "@/types/api/requests/lesson.request";
import { BookResponse } from "@/types/api/responses/lesson.response";
import BookSelect from "@/features/create-lesson/components/book.select";

const CreateGrammarLessonForm = ({ books }: { books: BookResponse[] }) => {
    const t = useTranslations();
    const { setFiles, errorMessage, setErrorMessage, files, selectedLevel } =
        useGrammarLessonCreate();
    const [bookId, setBookId] = useState(books[0].id);

    const { replace } = useRouter();
    const searchParams = useSearchParams();

    const folder = searchParams.get("folder");
    const folderId = parsePositiveInt(folder?.split("-").pop());

    const [isPending, startTransition] = useTransition();

    const handleSubmit = async (event: React.SubmitEvent<HTMLFormElement>) => {
        event.preventDefault();

        startTransition(async () => {
            if (files.length === 0) {
                setErrorMessage(
                    t("Pages.createLesson.youMustAttachAtLeastOneFile"),
                );
            } else {
                const request: GrammarLessonRequest = {
                    folderId,
                    bookId,
                    jlptLevel: selectedLevel.jlptLevel,
                    files,
                };

                const response = await createGrammarLessons(request);
                if (response.statusCode === 201) {
                    if (folder) {
                        replace({
                            pathname: "/your-library/folder/[slug]",
                            params: { slug: folder },
                        });
                    } else {
                        replace("/your-library/lesson");
                    }
                } else {
                    setErrorMessage(response.clientMessage);
                }
            }
        });
    };

    return (
        <div className="flex flex-col gap-y-3">
            <WrapBox>
                <form onSubmit={handleSubmit}>
                    <div className="flex items-center justify-between">
                        <h1 className="font-semibold">
                            {t(
                                "Pages.yourLibrary.lesson.createNewGrammarLesson",
                            )}
                        </h1>
                        <Button
                            color="primary"
                            variant="contained"
                            type="submit"
                            loading={isPending}
                        >
                            {t("Common.create")}
                        </Button>
                    </div>
                </form>
            </WrapBox>

            <WrapBox>
                <div className="flex flex-col gap-y-3">
                    <GrammarJlptLevelSelect />
                    <BookSelect
                        books={books}
                        bookId={bookId}
                        setBookId={setBookId}
                    />

                    <div className="flex items-center gap-x-3">
                        <FilesUploadButton
                            setFiles={setFiles}
                            errorMessage={errorMessage}
                            setErrorMessage={setErrorMessage}
                        />

                        <Button
                            variant="outlined"
                            color="primary"
                            sx={{ width: "max-content", columnGap: "8px" }}
                        >
                            <PublicOutlinedIcon />
                            {t("Common.scope.public")}
                        </Button>
                    </div>

                    {errorMessage !== "" && (
                        <span className="text-tc-error mt-1 ml-1 flex items-center gap-x-1 text-[12px] font-semibold">
                            <WarningAmberOutlinedIcon
                                sx={{ fontSize: "14px" }}
                            />
                            {errorMessage}
                        </span>
                    )}
                </div>
            </WrapBox>
        </div>
    );
};

export default CreateGrammarLessonForm;
