import KanjiExplore from "@/features/kanji/components/kanji.explore";
import KanjiSearchBox from "@/features/kanji/components/kanji.search.box";
import { ActiveKanjiTabProvider } from "@/features/kanji/contexts/active.kanji.tab";
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
        title: t("kanji"),
    };
}

const KanjiPage = () => {
    return (
        <ActiveKanjiTabProvider>
            <div className="flex flex-col gap-y-5">
                <KanjiSearchBox />
                <KanjiExplore />
            </div>
        </ActiveKanjiTabProvider>
    );
};

export default KanjiPage;
