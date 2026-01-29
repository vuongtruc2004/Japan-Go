import KanjiExplore from "@/layouts/pages/kanji/components/kanji.explore";
import KanjiSearchBox from "@/layouts/pages/kanji/features/kanji.search.box";
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
        title: t("kanji"),
    };
}

const KanjiPage = () => {
    return (
        <div className="flex flex-col gap-y-5">
            <KanjiSearchBox />
            <KanjiExplore />
        </div>
    );
};

export default KanjiPage;
