import React from "react";
import { getTranslations } from "next-intl/server";
import { KanjiDataProvider } from "@/features/create-lesson/contexts/kanji.data";
import CreateKanjiLesson from "@/features/create-lesson/components/kanji/create.kanji.lesson";

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
        title: t("create-lesson/kanji"),
    };
}

const CreateKanjiLessonPage = () => {
    return (
        <KanjiDataProvider>
            <CreateKanjiLesson />
        </KanjiDataProvider>
    );
};

export default CreateKanjiLessonPage;
