"use client";
import React, { useState, useTransition } from "react";
import { useTranslations } from "next-intl";
import { Button } from "@mui/material";
import WrapBox from "@/components/ui/wrap.box";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import PublicOutlinedIcon from "@mui/icons-material/PublicOutlined";
import KanjiDataImportButton from "@/features/create-lesson/components/kanji/kanji.data.import.button";
import { ICreateKanjiLessonState } from "@/features/create-lesson/types/create.lesson.state.type";
import { createKanjiLesson } from "@/features/create-lesson/actions/create.lesson.actions";
import { useKanjiData } from "@/features/create-lesson/contexts/kanji.data";
import { useSearchParams } from "next/navigation";
import { useRouter } from "@/i18n/navigation";
import { parsePositiveInt } from "@/utils/parse.util";

const CreateKanjiLessonForm = () => {
    const t = useTranslations();
    const { kanjiPages } = useKanjiData();
    const { replace } = useRouter();
    const searchParams = useSearchParams();

    const folder = searchParams.get("folder");
    const folderId = parsePositiveInt(folder?.split("-").pop());

    const [formState, setFormState] = useState<ICreateKanjiLessonState | null>(
        null,
    );
    const [isPending, startTransition] = useTransition();

    const formAction = (formData: FormData) => {
        startTransition(async () => {
            const formState = await createKanjiLesson(
                formData,
                kanjiPages,
                folderId,
            );
            startTransition(() => {
                if (
                    formState.apiResponse &&
                    formState.apiResponse.statusCode === 201
                ) {
                    if (folder) {
                        replace({
                            pathname: "/your-library/folder/[slug]",
                            params: { slug: folder },
                        });
                    } else {
                        replace("/your-library/folder");
                    }
                } else {
                    setFormState(formState);
                }
            });
        });
    };

    return (
        <WrapBox>
            <form action={formAction} className="flex flex-col gap-y-3">
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
                    defaultValue={formState ? formState.lessonName.value : ""}
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
                    defaultValue={formState ? formState.description.value : ""}
                />

                <div className="flex items-center gap-x-3">
                    <KanjiDataImportButton />

                    <Button
                        variant="outlined"
                        color="primary"
                        sx={{ width: "max-content", columnGap: "8px" }}
                    >
                        <PublicOutlinedIcon />
                        {t("Common.scope.public")}
                    </Button>
                </div>
            </form>
        </WrapBox>
    );
};

export default CreateKanjiLessonForm;
