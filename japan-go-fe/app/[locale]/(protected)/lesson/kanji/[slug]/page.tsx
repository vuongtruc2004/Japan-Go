import React from "react";
import { Metadata } from "next";
import { getLessonById } from "@/services/lesson.service";
import KanjiPageMoveButtons from "@/features/lesson/components/kanji/kanji.page.move.buttons";
import { ActiveKanjiPageProvider } from "@/features/lesson/contexts/active.kanji.page";
import ActiveKanji from "@/features/lesson/components/kanji/active.kanji";
import LessonHeader from "@/features/lesson/components/common/lesson.header";
import { VocabularyVisibilityProvider } from "@/features/lesson/contexts/vocabulary.visibility";
import VocabularyVisibilityButtons from "@/features/lesson/components/kanji/vocabulary.visibility.buttons";

const getKanjiLessonFromParams = async (params: Promise<{ slug: string }>) => {
    const { slug } = await params;
    const id = slug.split("-").pop();
    if (!id) {
        throw new Error("invalidPath");
    }
    return getLessonById(id);
};

export async function generateMetadata({
    params,
}: {
    params: Promise<{ slug: string }>;
}): Promise<Metadata> {
    const lesson = await getKanjiLessonFromParams(params);
    return {
        title: lesson.lessonName,
    };
}

const KanjiLessonPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const lesson = await getKanjiLessonFromParams(params);

    return (
        <VocabularyVisibilityProvider>
            <ActiveKanjiPageProvider>
                <div className="relative mx-auto flex max-w-350 items-start gap-x-5">
                    <div className="flex flex-1 flex-col gap-y-5">
                        <LessonHeader lesson={lesson} />

                        <VocabularyVisibilityButtons />

                        <ActiveKanji
                            kanjiPages={lesson.kanjiLesson.kanjiPages}
                        />
                    </div>
                    <KanjiPageMoveButtons
                        kanjiPages={lesson.kanjiLesson.kanjiPages}
                    />
                </div>
            </ActiveKanjiPageProvider>
        </VocabularyVisibilityProvider>
    );
};

export default KanjiLessonPage;
