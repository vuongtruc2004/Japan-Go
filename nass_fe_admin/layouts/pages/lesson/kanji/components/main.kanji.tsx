"use client";
import { useKanjiSlide } from "@/libs/wrapper/context/kanji.slide.wrapper";
import KanjiVgDraw from "../features/kanji.vg.draw";

const MainKanji = () => {
    const { activeSlideIndex, lesson } = useKanjiSlide();
    const kanji = lesson.kanjiLesson.kanjiPages[activeSlideIndex].mainKanji;

    return (
        <div className="bg-bgc-app border-bdc-primary w-max rounded-md border p-5">
            <div className="border-bdc-primary bg-bgc-page relative mb-3 flex items-center justify-center rounded-md border">
                <KanjiVgDraw svg={kanji.svg} />
                <span className="text-tc-highlight absolute bottom-0 left-1/2 -translate-x-1/2 text-lg font-semibold">
                    「 {kanji.mainSinoVietnamese} 」
                </span>
            </div>

            {kanji.onyomiList.length > 0 && (
                <>
                    <p className="text-tc-highlight font-semibold">Âm On:</p>
                    {kanji.onyomiList.map((onyomi) => {
                        return <p key={onyomi}>{onyomi}</p>;
                    })}
                </>
            )}

            {kanji.kunyomiList.length > 0 && (
                <>
                    <p className="text-tc-highlight font-semibold">Âm Kun:</p>
                    {kanji.kunyomiList.map((kunyomi) => {
                        return <p key={kunyomi}>{kunyomi}</p>;
                    })}
                </>
            )}
        </div>
    );
};

export default MainKanji;
