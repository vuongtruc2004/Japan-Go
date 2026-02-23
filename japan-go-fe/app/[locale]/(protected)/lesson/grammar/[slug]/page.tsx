import React from "react";
import { Metadata } from "next";
import { getLessonById } from "@/services/lesson.service";
import LessonHeader from "@/features/lesson/components/common/lesson.header";
import GrammarDetails from "@/features/lesson/components/grammar/grammar.details";
import GrammarScrollSidebar from "@/features/lesson/components/grammar/grammar.scroll.sidebar";

const getGrammarLessonFromParams = async (
    params: Promise<{ slug: string }>,
) => {
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
    const lesson = await getGrammarLessonFromParams(params);
    return {
        title: lesson.lessonName,
    };
}

const GrammarLessonPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const lesson = await getGrammarLessonFromParams(params);

    return (
        <div className="mx-auto flex max-w-350 items-start gap-x-5">
            <div className="flex flex-col gap-y-5">
                <LessonHeader lesson={lesson} />
                <GrammarDetails grammars={lesson.grammarLesson.grammars} />
            </div>
            <GrammarScrollSidebar grammars={lesson.grammarLesson.grammars} />
        </div>
    );
};

export default GrammarLessonPage;
