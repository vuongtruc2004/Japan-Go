import React from "react";
import { Metadata } from "next";
import { getLessonById } from "@/services/lesson.service";
import KanjiPageMoveButtons from "@/features/lesson/components/kanji/kanji.page.move.buttons";
import { ActiveKanjiPageProvider } from "@/features/lesson/contexts/active.kanji.page";

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
    const kanjiLesson = await getKanjiLessonFromParams(params);
    return {
        title: kanjiLesson.lessonName,
    };
}

const KanjiLessonPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const kanjiLesson = await getKanjiLessonFromParams(params);
    return (
        <ActiveKanjiPageProvider>
            <KanjiPageMoveButtons
                kanjiPages={kanjiLesson.kanjiLesson.kanjiPages}
            />
        </ActiveKanjiPageProvider>
    );
};

export default KanjiLessonPage;
