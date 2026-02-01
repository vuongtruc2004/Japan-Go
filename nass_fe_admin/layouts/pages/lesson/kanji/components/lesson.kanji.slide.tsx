"use client";

import { useActiveKanjiSlide } from "@/libs/wrapper/context/active.kanji.slide.wrapper";
import SingleVocabulary from "../features/single.vocabulary";
import VocabularyShowHideButtons from "../features/vocabulary.show.hide.buttons";
import MainKanji from "./main.kanji";

const LessonKanjiSlide = () => {
    const { lesson, activeIndex } = useActiveKanjiSlide();
    const activeKanjiPage = lesson.kanjiLesson.kanjiPages[activeIndex];

    return (
        <div className="bg-bgc-app border-bdc-primary w-full rounded-md border p-5">
            <div className="mb-3 flex items-center justify-between">
                <h1 className="text-tc-primary text-lg font-semibold">
                    Chi tiết:
                </h1>
                <VocabularyShowHideButtons />
            </div>

            <div className="flex items-start gap-x-3">
                <MainKanji kanji={activeKanjiPage.mainKanji} />

                <div className="flex flex-col gap-y-3">
                    {activeKanjiPage.vocabularies.map((vocabulary) => {
                        return (
                            <SingleVocabulary
                                key={vocabulary.id}
                                vocabulary={vocabulary}
                            />
                        );
                    })}
                </div>
            </div>
        </div>
    );
};

export default LessonKanjiSlide;
