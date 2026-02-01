import KanjiExplore from "@/layouts/pages/kanji/components/kanji.explore";
import KanjiSearchBox from "@/layouts/pages/kanji/features/kanji.search.box";
import { ActiveKanjiTabWrapper } from "@/libs/wrapper/context/active.kanji.tab.wrapper";
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
        <ActiveKanjiTabWrapper>
            <div className="flex flex-col gap-y-5">
                <KanjiSearchBox />
                <KanjiExplore />
            </div>
        </ActiveKanjiTabWrapper>
    );
};

export default KanjiPage;
