"use client";
import React, { useEffect, useRef } from "react";
import { KanjiPageResponse } from "@/types/api/responses/kanji.response";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import { useActiveKanjiPage } from "@/features/lesson/contexts/active.kanji.page";
import { getKanjiPageMoveButtonClassname } from "@/features/lesson/utils/lesson.utils";

const KanjiPageMoveButtons = ({
    kanjiPages,
}: {
    kanjiPages: KanjiPageResponse[];
}) => {
    const kanjiPagesLength = kanjiPages.length;

    const t = useTranslations();
    const { activeIndex, setActiveIndex } = useActiveKanjiPage();
    const buttonRefs = useRef<(HTMLButtonElement | null)[]>([]);

    useEffect(() => {
        if (!kanjiPagesLength) return;

        const onKeyDown = (e: KeyboardEvent) => {
            if (e.key === "ArrowLeft") {
                setActiveIndex((prev) =>
                    prev === 0 ? kanjiPagesLength - 1 : prev - 1,
                );
            } else if (e.key === "ArrowRight") {
                setActiveIndex((prev) =>
                    prev === kanjiPagesLength - 1 ? 0 : prev + 1,
                );
            }
        };

        window.addEventListener("keydown", onKeyDown, { passive: false });
        return () => window.removeEventListener("keydown", onKeyDown);
    }, [kanjiPagesLength, setActiveIndex]);

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
        <div className="bg-bgc-app border-bdc-primary rounded-md border px-5">
            <div className="flex items-center gap-x-3 overflow-x-auto py-5">
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
                            className={`relative flex w-28 shrink-0 cursor-pointer items-center justify-center gap-x-1 rounded-md border py-2 transition-all duration-150 select-none ${activeClass}`}
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
        </div>
    );
};

export default KanjiPageMoveButtons;
