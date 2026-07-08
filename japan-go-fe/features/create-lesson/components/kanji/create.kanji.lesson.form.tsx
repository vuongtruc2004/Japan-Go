"use client";
import React, { useState, useTransition } from "react";
import { useTranslations } from "next-intl";
import { Button } from "@mui/material";
import WrapBox from "@/components/ui/wrap.box";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import PublicOutlinedIcon from "@mui/icons-material/PublicOutlined";
import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import KanjiDataImportButton from "@/features/create-lesson/components/kanji/kanji.data.import.button";
import { useSearchParams } from "next/navigation";
import { useRouter } from "@/i18n/navigation";
import { parsePositiveInt } from "@/utils/parse.util";
import { BookResponse } from "@/types/api/responses/lesson.response";
import BookSelect from "@/features/create-lesson/components/book.select";
import { importKanjiLesson } from "@/services/lesson.service";
import { LessonType } from "@/types/enums/lesson.enum";

const CreateKanjiLessonForm = ({ books }: { books: BookResponse[] }) => {
    const t = useTranslations();
    const { replace } = useRouter();
    const searchParams = useSearchParams();
    const [bookId, setBookId] = useState(books[0].id);
    const [file, setFile] = useState<File | null>(null);
    const [errorMessage, setErrorMessage] = useState("");
    const [isPending, startTransition] = useTransition();

    const folder = searchParams.get("folder");
    const folderId = parsePositiveInt(folder?.split("-").pop());

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const lessonName = formData.get("lesson-name")?.toString().trim() || "";
        const description = formData.get("description")?.toString().trim() || "";

        if (!lessonName) {
            setErrorMessage(t("Pages.yourLibrary.lesson.inputLessonNamePlaceholder"));
            return;
        }

        if (!file) {
            setErrorMessage(t("Pages.createLesson.youMustAttachAtLeastOneFile"));
            return;
        }

        startTransition(async () => {
            try {
                await importKanjiLesson({
                    folderId,
                    bookId,
                    lessonName,
                    description,
                    lessonType: LessonType.KANJI,
                    file,
                });

                if (folder) {
                    replace({
                        pathname: "/your-library/folder/[slug]",
                        params: { slug: folder },
                    });
                } else {
                    replace("/your-library/lesson");
                }
            } catch (error: any) {
                setErrorMessage(error.message || "An error occurred");
            }
        });
    };

    return (
        <WrapBox>
            <form onSubmit={handleSubmit} className="flex flex-col gap-y-3">
                <div className="flex items-center justify-between">
                    <h1 className="font-semibold">
                        {t("Pages.yourLibrary.lesson.createNewKanjiLesson")}
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

                <TextFieldCustom
                    name="lesson-name"
                    placeholder={t(
                        "Pages.yourLibrary.lesson.inputLessonNamePlaceholder",
                    )}
                    fullWidth
                    size="small"
                />

                <TextFieldCustom
                    name="description"
                    placeholder={t(
                        "Pages.yourLibrary.lesson.addDescriptionPlaceholder",
                    )}
                    fullWidth
                    multiline
                    minRows={3}
                    maxRows={6}
                />

                <BookSelect
                    books={books}
                    bookId={bookId}
                    setBookId={setBookId}
                />

                <div className="flex items-center gap-x-3">
                    <KanjiDataImportButton
                        file={file}
                        setFile={setFile}
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
                        <WarningAmberOutlinedIcon sx={{ fontSize: "14px" }} />
                        {errorMessage}
                    </span>
                )}
            </form>
        </WrapBox>
    );
};

export default CreateKanjiLessonForm;
