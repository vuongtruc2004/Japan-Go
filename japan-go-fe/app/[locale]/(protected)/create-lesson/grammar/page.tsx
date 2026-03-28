import React from "react";
import CreateGrammarLesson from "@/features/create-lesson/components/grammar/create.grammar.lesson";
import { getTranslations } from "next-intl/server";
import { GrammarLessonCreateProvider } from "@/features/create-lesson/contexts/grammar.lesson.create.provider";
import { getAllBooks } from "@/services/lesson.service";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "Common.links" });

    return {
        title: t("create-lesson/grammar"),
    };
}

const CreateGrammarLessonPage = async () => {
    const response = await getAllBooks();
    return (
        <GrammarLessonCreateProvider>
            <CreateGrammarLesson books={response.data} />
        </GrammarLessonCreateProvider>
    );
};

export default CreateGrammarLessonPage;
