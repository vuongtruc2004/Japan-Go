import React from "react";
import WrapBox from "@/components/ui/wrap.box";
import { getKanjiByKanjiCharacter } from "@/services/kanji.service";
import Empty from "@/components/ui/empty";
import { getTranslations } from "next-intl/server";
import KanjiDetails from "@/features/kanji-details/components/kanji.details";

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
        title: t("kanji/details"),
    };
}

const KanjiDetailsPage = async ({
    searchParams,
}: {
    searchParams: Promise<{
        searchKey?: string;
    }>;
}) => {
    const t = await getTranslations("Pages.kanjiDetails");

    const { searchKey = "" } = await searchParams;
    const response = await getKanjiByKanjiCharacter(searchKey);

    if (!response.data) {
        return (
            <WrapBox>
                <Empty text={t("noKanji")} />
            </WrapBox>
        );
    }
    return (
        <WrapBox>
            <KanjiDetails kanji={response.data} />
        </WrapBox>
    );
};

export default KanjiDetailsPage;
