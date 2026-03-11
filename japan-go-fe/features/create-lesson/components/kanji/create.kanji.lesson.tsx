"use client";
import React from "react";
import CreateKanjiLessonForm from "@/features/create-lesson/components/kanji/create.kanji.lesson.form";
import { useKanjiData } from "@/features/create-lesson/contexts/kanji.data.provider";
import Empty from "@/components/ui/empty";
import SingleKanjiDataPreview from "@/features/create-lesson/components/kanji/single.kanji.data.preview";
import { useTranslations } from "next-intl";
import WrapBox from "@/components/ui/wrap.box";

const CreateKanjiLesson = () => {
    const t = useTranslations();
    const { kanjiPages } = useKanjiData();
    return (
        <div className="flex flex-col gap-y-5">
            <CreateKanjiLessonForm />

            <WrapBox>
                <div className="flex flex-col gap-y-3">
                    {kanjiPages.length === 0 ? (
                        <Empty
                            text={t("Pages.createLesson.noKanjiDataToPreview")}
                        />
                    ) : (
                        kanjiPages.map((kanjiPage) => {
                            return (
                                <SingleKanjiDataPreview
                                    key={kanjiPage.mainKanjiCharacter}
                                    kanjiPage={kanjiPage}
                                />
                            );
                        })
                    )}
                </div>
            </WrapBox>
        </div>
    );
};

export default CreateKanjiLesson;
