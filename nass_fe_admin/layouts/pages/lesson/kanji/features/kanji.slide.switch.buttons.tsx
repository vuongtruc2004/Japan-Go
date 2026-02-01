"use client";
import Empty from "@/components/common/empty";
import { useActiveKanjiSlide } from "@/libs/wrapper/context/active.kanji.slide.wrapper";

const SlideSwitchButtons = () => {
    const { lesson } = useActiveKanjiSlide();
    const kanjiLesson = lesson.kanjiLesson;
    const { activeIndex, setActiveIndex } = useActiveKanjiSlide();

    return (
        <div className="bg-bgc-app border-bdc-primary w-max rounded-md border p-5">
            {kanjiLesson && kanjiLesson.kanjiPages.length > 0 ? (
                <div className="flex flex-col gap-y-3">
                    {kanjiLesson.kanjiPages.map((page, index) => {
                        const activeClass =
                            index === activeIndex
                                ? "text-green-700 dark:text-green-500 bg-green-500/10 border-green-500/20"
                                : "hover:bg-hbgc-app transition-all duration-150 border-bdc-primary hover:border-bdc-primary";
                        return (
                            <div
                                key={page.id}
                                onClick={() => setActiveIndex(index)}
                                className={`${activeClass} font-noto-sans-jp bg-bgc-page flex h-14 w-14 cursor-pointer items-center justify-center rounded-md border px-3 text-2xl`}
                            >
                                {page.mainKanji.kanjiCharacter}
                            </div>
                        );
                    })}
                </div>
            ) : (
                <Empty text="Không có slide nào để hiển thị" />
            )}
        </div>
    );
};

export default SlideSwitchButtons;
