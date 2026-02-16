"use client";
import React, { useEffect, useState } from "react";
import { useTranslations } from "next-intl";
import { Button } from "@mui/material";
import WrapBox from "@/components/ui/wrap.box";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import PublicOutlinedIcon from "@mui/icons-material/PublicOutlined";
import KanjiDataImportButton from "@/features/create-course/components/kanji/kanji.data.import.button";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";

const CreateKanjiCourseForm = () => {
    const t = useTranslations();
    const [kanjiPages, setKanjiPages] = useState<KanjiPageRequest[]>([]);

    useEffect(() => {
        if (kanjiPages.length !== 0) {
            console.log(kanjiPages);
        }
    }, [kanjiPages]);

    return (
        <WrapBox>
            <form action="" className="flex flex-col gap-y-3">
                <div className="flex items-center justify-between">
                    <h1 className="font-semibold">
                        {t("Pages.yourLibrary.course.createNewKanjiCourse")}
                    </h1>
                    <Button color="primary" variant="contained" type="submit">
                        {t("Common.create")}
                    </Button>
                </div>

                <TextFieldCustom
                    id="input-lesson-name"
                    name="lesson-name"
                    placeholder={t(
                        "Pages.yourLibrary.course.inputLessonNamePlaceholder",
                    )}
                    fullWidth
                    size={"small"}
                />

                <TextFieldCustom
                    id="input-description"
                    name="description"
                    placeholder={t(
                        "Pages.yourLibrary.course.addDescriptionPlaceholder",
                    )}
                    fullWidth
                    multiline
                    minRows={3}
                    maxRows={6}
                />

                <div className="flex items-center gap-x-3">
                    <KanjiDataImportButton setKanjiPages={setKanjiPages} />

                    <Button
                        variant="outlined"
                        color="primary"
                        sx={{ width: "max-content", columnGap: "8px" }}
                    >
                        <PublicOutlinedIcon />
                        Công khai
                    </Button>
                </div>
            </form>
        </WrapBox>
    );
};

export default CreateKanjiCourseForm;
