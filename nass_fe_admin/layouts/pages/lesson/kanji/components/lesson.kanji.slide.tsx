"use client";

import { useKanjiSlide } from "@/libs/wrapper/context/kanji.slide.wrapper";
import SingleVocabulary from "../features/single.vocabulary";
import VocabularyVisibilityButtons from "../features/vocabulary.visibility.buttons";

const LessonKanjiSlide = () => {
    const { lesson, activeSlideIndex } = useKanjiSlide();
    const activeKanjiPage = lesson.kanjiLesson.kanjiPages[activeSlideIndex];

    return (
        <div className="bg-bgc-app border-bdc-primary w-full rounded-md border p-5">
            <div className="mb-3 flex items-center justify-between">
                <h1 className="text-tc-primary font-semibold">
                    Danh sách các từ:
                </h1>
                <VocabularyVisibilityButtons />
            </div>

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
    );
};

export default LessonKanjiSlide;
