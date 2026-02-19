"use client";
import React from "react";
import { useActiveKanjiPage } from "@/features/lesson/contexts/active.kanji.page";
import { KanjiPageResponse } from "@/types/api/responses/kanji.response";
import WrapBox from "@/components/ui/wrap.box";
import KanjiVgAnimator from "@/features/lesson/components/kanji/kanji.vg.animator";
import TextLargeView from "@/features/lesson/components/kanji/text.large.view";
import { useTranslations } from "next-intl";
import JapaneseView from "@/features/lesson/components/kanji/japanese.view";
import VocabularyVisibilityButtons from "@/features/lesson/components/kanji/vocabulary.visibility.buttons";
import { Divider } from "@mui/material";
import { useVocabularyVisibility } from "@/features/lesson/contexts/vocabulary.visibility";

const ActiveKanjiPage = ({
    kanjiPages,
}: {
    kanjiPages: KanjiPageResponse[];
}) => {
    const t = useTranslations("Pages.lesson.kanji");
    const { activeIndex } = useActiveKanjiPage();
    const {
        showJapanese,
        showSinoVietnamese,
        showReading,
        showMeaning,
        showNote,
    } = useVocabularyVisibility();

    const kanjiPage = kanjiPages[activeIndex];
    const mainKanji = kanjiPage.mainKanji;

    return (
        <WrapBox>
            <VocabularyVisibilityButtons />

            <Divider sx={{ my: 3 }} />

            <div className="flex gap-x-5">
                <div className="border-bdc-primary flex flex-col gap-y-5">
                    <div className="relative">
                        <KanjiVgAnimator kanjiVg={mainKanji.kanjiVg} />
                        <span className="border-bdc-primary bg-bgc-app absolute bottom-0 left-1/2 -translate-x-1/2 translate-y-1/2 rounded-md border px-4 py-2 text-lg font-semibold">
                            {mainKanji.mainSinoVietnamese}
                        </span>
                    </div>
                    <div className="mt-3 max-w-54">
                        <p>
                            <span className="font-semibold">
                                {t("onyomi")}:{" "}
                            </span>
                            <span className="font-noto-sans-jp">
                                {mainKanji.onyomiList.join("／")}
                            </span>
                        </p>
                        <p>
                            <span className="font-semibold">
                                {t("kunyomi")}:{" "}
                            </span>
                            <span className="font-noto-sans-jp">
                                {mainKanji.kunyomiList.join("／")}
                            </span>
                        </p>
                    </div>
                </div>
                <div className="flex flex-col gap-y-3">
                    {kanjiPage.vocabularies.map((vocabulary) => {
                        return (
                            <div
                                key={vocabulary.id}
                                className="flex items-center gap-x-3"
                            >
                                {showJapanese && (
                                    <JapaneseView
                                        japanese={vocabulary.japanese}
                                    />
                                )}
                                {showSinoVietnamese && (
                                    <TextLargeView
                                        text={vocabulary.sinoVietnamese}
                                        isSemibold={true}
                                    />
                                )}
                                {showReading && (
                                    <JapaneseView
                                        japanese={vocabulary.reading}
                                    />
                                )}

                                {showMeaning && (
                                    <TextLargeView
                                        text={vocabulary.meaning}
                                        isSemibold={true}
                                    />
                                )}

                                {Boolean(vocabulary.note.length) &&
                                    showNote && (
                                        <TextLargeView
                                            text={vocabulary.note}
                                            isSemibold={true}
                                        />
                                    )}
                            </div>
                        );
                    })}
                </div>
            </div>
        </WrapBox>
    );
};

export default ActiveKanjiPage;
