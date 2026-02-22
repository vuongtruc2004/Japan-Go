import { getTranslations } from "next-intl/server";
import WrapBox from "@/components/ui/wrap.box";
import { getAllLessons } from "@/services/lesson.service";
import LessonList from "@/features/your-library/components/lesson/lesson.list";

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
        title: t("lesson.title"),
    };
}

const LessonPage = async () => {
    const t = await getTranslations("Pages.yourLibrary");
    const response = await getAllLessons();
    const lessons = response.data.content;
    return (
        <WrapBox>
            <LessonList lessons={lessons} />
        </WrapBox>
    );
};

export default LessonPage;
