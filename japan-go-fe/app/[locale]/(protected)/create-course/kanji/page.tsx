import React from "react";
import CreateKanjiCourseForm from "@/features/create-course/components/kanji/create.kanji.course.form";
import { getTranslations } from "next-intl/server";

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
        title: t("create-course.kanji"),
    };
}

const CreateKanjiCoursePage = () => {
    return (
        <div>
            <CreateKanjiCourseForm />
        </div>
    );
};

export default CreateKanjiCoursePage;
