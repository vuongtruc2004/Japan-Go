import { getTranslations } from "next-intl/server";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "Common" });

    return {
        title: t("course"),
    };
}

const CoursePage = () => {
    return <div>CoursePage</div>;
};

export default CoursePage;
