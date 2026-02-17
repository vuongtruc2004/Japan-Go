"use client";
import React from "react";
import { KanjiPageResponse } from "@/types/api/responses/kanji.response";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import WrapBox from "@/components/ui/wrap.box";
import { getKanjiPageMoveButtonClassname } from "@/features/lesson/utils/lesson.utils";
import { useActiveKanjiPage } from "@/features/lesson/contexts/active.kanji.page";

const KanjiPageMoveButtons = ({
    kanjiPages,
}: {
    kanjiPages: KanjiPageResponse[];
}) => {
    const t = useTranslations();
    const { activeIndex, setActiveIndex } = useActiveKanjiPage();

    return (
        <WrapBox>
            <div>
                {kanjiPages.length === 0 ? (
                    <Empty text={t("Pages.lesson.kanji.noKanjiPages")} />
                ) : (
                    <div className="flex items-center gap-x-3">
                        {kanjiPages.map((kanjiPage, index) => {
                            const activeClass = getKanjiPageMoveButtonClassname(
                                activeIndex,
                                index,
                            );

                            return (
                                <button
                                    onClick={() => setActiveIndex(index)}
                                    key={kanjiPage.id}
                                    className={`relative flex w-22 cursor-pointer items-center justify-center gap-x-1 rounded-md border py-2 ${activeClass}`}
                                >
                                    <p className="absolute top-0.5 left-1.5 text-[12px] font-semibold">
                                        {index + 1}
                                    </p>
                                    <p className="font-noto-sans-jp text-lg">
                                        {kanjiPage.mainKanji.kanjiCharacter}
                                    </p>
                                </button>
                            );
                        })}
                    </div>
                )}
            </div>
        </WrapBox>
    );
};

export default KanjiPageMoveButtons;
