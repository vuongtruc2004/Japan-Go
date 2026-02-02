"use client";

import Empty from "@/components/common/empty";
import { useKanjiSlide } from "@/libs/wrapper/context/kanji.slide.wrapper";
import { useEffect, useRef } from "react";

const SlideSwitchButtons = () => {
    const { activeSlideIndex, setActiveSlideIndex, lesson } = useKanjiSlide();
    const kanjiLesson = lesson.kanjiLesson;

    const itemRefs = useRef<(HTMLDivElement | null)[]>([]);

    useEffect(() => {
        const element = itemRefs.current[activeSlideIndex];
        if (element) {
            element.scrollIntoView({
                behavior: "smooth",
                block: "nearest",
                inline: "center",
            });
        }
    }, [activeSlideIndex]);

    return (
        <div className="bg-bgc-app border-bdc-primary my-3 rounded-md border px-5">
            {kanjiLesson && kanjiLesson.kanjiPages.length > 0 ? (
                <div className="w-full overflow-auto">
                    <div className="flex w-max items-center gap-3 py-5">
                        {kanjiLesson.kanjiPages.map((page, index) => {
                            const isActive = index === activeSlideIndex;

                            const activeClass = isActive
                                ? "text-green-700 dark:text-green-500 bg-green-500/10 border-green-500/20"
                                : "hover:bg-hbgc-app transition-all duration-150 border-bdc-primary hover:border-bdc-primary";

                            return (
                                <div
                                    key={page.id}
                                    ref={(element) => {
                                        itemRefs.current[index] = element;
                                    }}
                                    onClick={() => setActiveSlideIndex(index)}
                                    className={`${activeClass} font-noto-sans-jp bg-bgc-page relative flex h-14 w-24 shrink-0 cursor-pointer items-center justify-center rounded-md border px-3 text-2xl`}
                                >
                                    <span
                                        className={`absolute top-1 left-2 text-[12px] font-semibold ${
                                            isActive
                                                ? "text-green-700 dark:text-green-500"
                                                : "text-tc-muted"
                                        }`}
                                    >
                                        {index + 1}
                                    </span>
                                    {page.mainKanji.kanjiCharacter}
                                </div>
                            );
                        })}
                    </div>
                </div>
            ) : (
                <Empty text="Không có slide nào để hiển thị" />
            )}
        </div>
    );
};

export default SlideSwitchButtons;
