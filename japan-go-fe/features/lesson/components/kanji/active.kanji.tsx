"use client";
import React from "react";
import { useActiveKanjiPage } from "@/features/lesson/contexts/active.kanji.page";
import { KanjiPageResponse } from "@/types/api/responses/kanji.response";
import KanjiVgAnimator from "@/features/lesson/components/kanji/kanji.vg.animator";
import { useTranslations } from "next-intl";
import JapaneseView from "@/features/lesson/components/kanji/japanese.view";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useVocabularyVisibilityToggles } from "@/features/lesson/hooks/use.vocabulary.visibility.toggles";
import { useVocabularyVisibility } from "@/features/lesson/contexts/vocabulary.visibility";

const ActiveKanji = ({ kanjiPages }: { kanjiPages: KanjiPageResponse[] }) => {
    const t = useTranslations("Pages.lesson.kanji");
    const { activeIndex } = useActiveKanjiPage();
    const toggles = useVocabularyVisibilityToggles();
    const {
        showJapanese,
        showSinoVietnamese,
        showReading,
        showMeaning,
        showNote,
        showStroke,
        showSupportVocabulary,
    } = useVocabularyVisibility();

    if (!kanjiPages || kanjiPages.length === 0) {
        return null;
    }

    const kanjiPage = kanjiPages[activeIndex];
    const mainKanji = kanjiPage.mainKanji;

    const getToggleColor = (id: string) => {
        switch (id) {
            case "stroke":
                return "bg-[#a855f7]";
            case "japanese":
                return "bg-[#27C93F]";
            case "sino-vietnamese":
                return "bg-[#25C6DA]";
            case "reading":
                return "bg-[#FFBD2E]";
            case "meaning":
                return "bg-[#FF8B3D]";
            case "note":
                return "bg-[#FF5F56]";
            case "supportVocabulary":
                return "bg-[#E91E63]";
            default:
                return "bg-neutral-400";
        }
    };

    return (
        <div className="flex w-full flex-col items-start gap-5 lg:flex-row">
            {/* Left Column: Main Kanji Info Card */}
            <div className="border-bdc-primary bg-bgc-app sticky flex h-max w-full shrink-0 flex-col items-center gap-y-4 rounded-xl border p-5 pt-9 shadow-sm lg:top-21.25 lg:w-64 dark:bg-zinc-900">
                {/* macOS Traffic Lights Toggles */}
                <div className="absolute top-4 left-5 flex items-center gap-x-1.5 select-none">
                    {toggles.map((toggle) => {
                        const active = toggle.visible;
                        const tooltipText = active
                            ? t(`hideVocabularyLabels.${toggle.hideLabelKey}`)
                            : t(`showVocabularyLabels.${toggle.showLabelKey}`);

                        return (
                            <TooltipCustom
                                key={toggle.id}
                                title={tooltipText}
                                placement="top"
                            >
                                <button
                                    onClick={() =>
                                        toggle.setVisible((prev) => !prev)
                                    }
                                    className={`group/btn flex h-3.5 w-3.5 cursor-pointer items-center justify-center rounded-full border border-black/10 shadow-sm transition-all duration-150 hover:scale-110 active:scale-90 dark:border-white/10 ${
                                        active
                                            ? getToggleColor(toggle.id)
                                            : "bg-neutral-300 hover:bg-neutral-400 dark:bg-zinc-700 dark:hover:bg-zinc-600"
                                    }`}
                                >
                                    <span
                                        className={`text-[8px] leading-none font-extrabold opacity-0 transition-opacity duration-150 select-none group-hover/btn:opacity-60 ${
                                            active
                                                ? "text-white"
                                                : "text-neutral-600 dark:text-neutral-300"
                                        }`}
                                    >
                                        {active ? "x" : "o"}
                                    </span>
                                </button>
                            </TooltipCustom>
                        );
                    })}
                </div>

                <div className="relative flex w-full justify-center pb-2">
                    <KanjiVgAnimator kanjiVg={mainKanji.kanjiVg} className="w-full aspect-square" />
                    <span className="border-bdc-primary bg-bgc-app text-tc-primary absolute bottom-0 left-1/2 -translate-x-1/2 translate-y-1/2 rounded-md border px-4 py-1.5 text-lg font-semibold shadow-sm select-none dark:bg-zinc-950">
                        {mainKanji.mainSinoVietnamese}
                    </span>
                </div>

                <div className="border-bdc-primary/40 text-tc-primary mt-4 flex w-full flex-col gap-y-3 border-t pt-4 text-sm">
                    <div>
                        <span className="mb-0.5 block text-[11px] font-bold tracking-wider text-neutral-400 uppercase select-none dark:text-zinc-500">
                            {t("onyomi")}
                        </span>
                        <span className="font-noto-sans-jp text-base font-semibold text-neutral-800 dark:text-neutral-200">
                            {mainKanji.onyomiList.length > 0
                                ? mainKanji.onyomiList.join("／")
                                : "—"}
                        </span>
                    </div>
                    <div>
                        <span className="mb-0.5 block text-[11px] font-bold tracking-wider text-neutral-400 uppercase select-none dark:text-zinc-500">
                            {t("kunyomi")}
                        </span>
                        <span className="font-noto-sans-jp text-base font-semibold text-neutral-800 dark:text-neutral-200">
                            {mainKanji.kunyomiList.length > 0
                                ? mainKanji.kunyomiList.join("／")
                                : "—"}
                        </span>
                    </div>
                </div>
            </div>

            {/* Right Column: Vocabulary Cards List */}
            <div className="flex w-full flex-1 flex-col gap-y-4">
                {kanjiPage.vocabularies.map((vocabulary) => {
                    const hasSupport =
                        vocabulary.supportVocabularies &&
                        vocabulary.supportVocabularies.length > 0;

                    return (
                        <div
                            key={vocabulary.id}
                            className="bg-bgc-app border-bdc-primary hover:border-tc-highlight/40 flex flex-col gap-y-3 rounded-xl border p-5 shadow-sm transition-all duration-200 hover:shadow-md dark:bg-zinc-900/60"
                        >
                            {/* Vocabulary Stroke Drawings Section using KanjiVgAnimator (Top) */}
                            {showStroke &&
                                vocabulary.kanjiVgList &&
                                vocabulary.kanjiVgList.length > 0 && (
                                    <div className="border-bdc-primary/40 flex flex-col gap-y-2 border-b pb-3">
                                        <span className="text-[10px] font-bold tracking-wider text-neutral-400 uppercase select-none dark:text-zinc-500">
                                            Nét vẽ chữ Hán (Strokes)
                                        </span>
                                        <div
                                            className="flex max-w-full flex-nowrap items-center justify-start gap-3 overflow-x-auto pb-2 [&::-webkit-scrollbar]:hidden"
                                            style={{
                                                scrollbarWidth: "none",
                                                msOverflowStyle: "none",
                                            }}
                                        >
                                            {vocabulary.kanjiVgList.map(
                                                (svg, idx) => {
                                                    const len =
                                                        vocabulary.kanjiVgList
                                                            .length;
                                                    const size =
                                                        len <= 1
                                                            ? 140
                                                            : len === 2
                                                              ? 112
                                                              : len === 3
                                                                ? 88
                                                                : 72;
                                                    return (
                                                        <KanjiVgAnimator
                                                            key={idx}
                                                            kanjiVg={svg}
                                                            size={size}
                                                        />
                                                    );
                                                },
                                            )}
                                        </div>
                                    </div>
                                )}

                            {/* Card Header: Word + SinoVietnamese */}
                            <div className="border-bdc-primary/40 flex flex-wrap items-center justify-between gap-3 border-b pb-2">
                                <div className="flex flex-wrap items-center gap-x-3 gap-y-2">
                                    {showJapanese && (
                                        <JapaneseView
                                            japanese={vocabulary.japanese}
                                        />
                                    )}
                                    {showSinoVietnamese &&
                                        vocabulary.sinoVietnamese && (
                                            <span className="rounded-md border border-blue-500/20 bg-blue-500/10 px-2.5 py-1 text-sm font-semibold tracking-wider text-blue-600 uppercase select-none dark:bg-blue-950/40 dark:text-blue-400">
                                                {vocabulary.sinoVietnamese}
                                            </span>
                                        )}
                                </div>
                            </div>

                            {/* Reading and Meaning Container */}
                            {(showReading || showMeaning) && (
                                <div className="flex flex-wrap items-center gap-x-5 gap-y-2 pt-1 text-base">
                                    {showReading && (
                                        <div className="text-tc-primary flex items-center gap-x-2">
                                            <span className="rounded bg-neutral-100 px-1.5 py-0.5 text-[10px] font-bold text-neutral-400 uppercase select-none dark:bg-zinc-800 dark:text-zinc-500">
                                                CÁCH ĐỌC
                                            </span>
                                            <span className="font-noto-sans-jp text-lg font-medium text-neutral-800 dark:text-neutral-200">
                                                {vocabulary.reading}
                                            </span>
                                        </div>
                                    )}
                                    {showReading && showMeaning && (
                                        <span className="hidden text-neutral-300 select-none sm:inline dark:text-zinc-700">
                                            |
                                        </span>
                                    )}
                                    {showMeaning && (
                                        <div className="flex items-center gap-x-2">
                                            <span className="rounded bg-neutral-100 px-1.5 py-0.5 text-[10px] font-bold text-neutral-400 uppercase select-none dark:bg-zinc-800 dark:text-zinc-500">
                                                NGHĨA
                                            </span>
                                            <span className="text-lg font-semibold text-neutral-900 dark:text-neutral-100">
                                                {vocabulary.meaning}
                                            </span>
                                        </div>
                                    )}
                                </div>
                            )}

                            {/* Note Section */}
                            {showNote &&
                                vocabulary.note &&
                                vocabulary.note.trim().length > 0 && (
                                    <div className="mt-1 flex items-start gap-x-2.5 rounded-lg border border-red-500/10 bg-red-500/5 p-3 text-sm text-red-700 dark:text-red-400">
                                        <span className="mt-0.5 rounded bg-red-500/10 px-1.5 py-0.5 text-[10px] font-bold tracking-wider text-red-500 uppercase select-none dark:text-red-400">
                                            CHÚ Ý
                                        </span>
                                        <span className="flex-1 leading-relaxed font-medium">
                                            {vocabulary.note}
                                        </span>
                                    </div>
                                )}

                            {/* Support Vocabulary Section */}
                            {showSupportVocabulary && hasSupport && (
                                <div className="border-bdc-primary mt-2 flex flex-col gap-y-2.5 rounded-r-lg border-l-2 bg-neutral-50/40 py-1.5 pr-3 pl-4 dark:bg-zinc-950/20">
                                    <span className="text-[10px] font-bold tracking-wider text-neutral-400 uppercase select-none dark:text-zinc-500">
                                        Từ bổ trợ
                                    </span>
                                    <div className="flex flex-col gap-y-2">
                                        {vocabulary.supportVocabularies.map(
                                            (support) => (
                                                <div
                                                    key={support.id}
                                                    className="flex flex-wrap items-center gap-x-3 gap-y-1 text-sm"
                                                >
                                                    {showJapanese && (
                                                        <JapaneseView
                                                            japanese={
                                                                support.japanese
                                                            }
                                                            isSmall={true}
                                                        />
                                                    )}
                                                    {showReading && (
                                                        <span className="font-noto-sans-jp text-neutral-600 dark:text-neutral-300">
                                                            {support.reading}
                                                        </span>
                                                    )}
                                                    {showReading &&
                                                        showMeaning && (
                                                            <span className="text-neutral-300 select-none dark:text-zinc-700">
                                                                —
                                                            </span>
                                                        )}
                                                    {showMeaning && (
                                                        <span className="font-semibold text-neutral-800 dark:text-neutral-200">
                                                            {support.meaning}
                                                        </span>
                                                    )}
                                                </div>
                                            ),
                                        )}
                                    </div>
                                </div>
                            )}
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default ActiveKanji;
