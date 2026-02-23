"use client";
import React, { useEffect, useRef } from "react";
import { KanjiPageResponse } from "@/types/api/responses/kanji.response";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import { useActiveKanjiPage } from "@/features/lesson/contexts/active.kanji.page";
import { getKanjiPageMoveButtonClassname } from "@/features/lesson/utils/lesson.utils";
import { useKanjiPageMoveByKeyboard } from "@/features/lesson/hooks/use.kanji.page.move.by.keyboard";

const KanjiPageMoveButtons = ({
    kanjiPages,
}: {
    kanjiPages: KanjiPageResponse[];
}) => {
    const kanjiPagesLength = kanjiPages.length;

    const t = useTranslations();
    const { activeIndex, setActiveIndex } = useActiveKanjiPage();
    const buttonRefs = useRef<(HTMLButtonElement | null)[]>([]);

    useKanjiPageMoveByKeyboard({
        length: kanjiPagesLength,
        setActiveIndex,
    });
    useEffect(() => {
        const currentButton = buttonRefs.current[activeIndex];
        if (currentButton) {
            currentButton.scrollIntoView({
                behavior: "smooth",
                inline: "center",
                block: "nearest",
            });
        }
    }, [activeIndex]);

    if (!kanjiPagesLength)
        return <Empty text={t("Pages.lesson.kanji.noKanjiPages")} />;

    return (
        <div className="bg-bgc-app border-bdc-primary sticky top-21.25 flex h-[calc(100vh-105px)] w-max shrink-0 flex-col gap-y-3 overflow-y-auto rounded-md border p-5">
            {kanjiPages.map((kanjiPage, index) => {
                const activeClass = getKanjiPageMoveButtonClassname(
                    activeIndex,
                    index,
                );

                return (
                    <button
                        ref={(el) => {
                            buttonRefs.current[index] = el;
                        }}
                        key={kanjiPage.id}
                        onClick={() => setActiveIndex(index)}
                        className={`relative flex h-18 w-18 shrink-0 cursor-pointer items-center justify-center gap-x-1 rounded-md border transition-all duration-150 select-none ${activeClass}`}
                    >
                        <p className="absolute top-0.5 left-1.5 text-[12px] font-semibold">
                            {index + 1}
                        </p>
                        <p className="font-noto-sans-jp text-xl">
                            {kanjiPage.mainKanji.kanjiCharacter}
                        </p>
                    </button>
                );
            })}
        </div>
    );
};

export default KanjiPageMoveButtons;
