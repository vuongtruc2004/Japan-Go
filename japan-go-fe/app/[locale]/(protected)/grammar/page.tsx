import React from "react";
import { getTranslations } from "next-intl/server";
import Grammar from "@/features/grammar/components/grammar";
import { getAllGrammars } from "@/services/grammar.service";

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
        title: t("grammar"),
    };
}

const GrammarPage = async ({}: {param: {

    }}) => {
    const grammars = await getAllGrammars();
    return <Grammar />;
};

export default GrammarPage;
