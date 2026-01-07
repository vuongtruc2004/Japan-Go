import Course from "@/layouts/pages/course/components/course";
import { getTranslations } from "next-intl/server";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "AppSidebar.links" });

    return {
        title: t("course"),
    };
}

const CoursePage = () => {
    return <Course />;
};

export default CoursePage;
