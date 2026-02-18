import React from "react";
import { Metadata } from "next";
import { getLessonById } from "@/services/lesson.service";
import KanjiPageMoveButtons from "@/features/lesson/components/kanji/kanji.page.move.buttons";
import { ActiveKanjiPageProvider } from "@/features/lesson/contexts/active.kanji.page";
import ActiveKanjiPage from "@/features/lesson/components/kanji/active.kanji.page";
import LessonHeader from "@/features/lesson/components/lesson.header";

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
        <ActiveKanjiPageProvider>
            <div className="flex flex-col gap-y-5">
                <LessonHeader lesson={lesson} />
                <KanjiPageMoveButtons
                    kanjiPages={lesson.kanjiLesson.kanjiPages}
                />
                <ActiveKanjiPage kanjiPages={lesson.kanjiLesson.kanjiPages} />
            </div>
        </ActiveKanjiPageProvider>
    );
};

export default KanjiLessonPage;
