import Kanji from "@/layouts/pages/kanji/kanji";

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
    return <Kanji />;
};

export default KanjiPage;
