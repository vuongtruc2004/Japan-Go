import React from "react";
import CreateGrammarLesson from "@/features/create-lesson/components/grammar/create.grammar.lesson";
import { getTranslations } from "next-intl/server";
import { GrammarFilesUploadProvider } from "@/features/create-lesson/contexts/grammar.files.upload";

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

const CreateGrammarLessonPage = () => {
    return (
        <GrammarFilesUploadProvider>
            <CreateGrammarLesson />
        </GrammarFilesUploadProvider>
    );
};

export default CreateGrammarLessonPage;
