import React from "react";
import { getTranslations } from "next-intl/server";
import { getAllGrammars } from "@/services/grammar.service";
import { GrammarSearchRequest } from "@/types/api/requests/grammar.request";
import { PageRequest } from "@/types/app/share.type";
import GrammarSearchBox from "@/features/grammar/components/grammar.search.box";
import GrammarList from "@/features/grammar/components/grammar.list";

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

const GrammarPage = async ({
    searchParams,
}: {
    searchParams: Promise<{
        searchKey?: string;
        page?: string;
        size?: string;
    }>;
}) => {
    const { searchKey = "", page = "1", size = "10" } = await searchParams;

    const searchRequest: GrammarSearchRequest = {
        searchKey,
    };

    const pageRequest: PageRequest = {
        page,
        size,
    };

    const response = await getAllGrammars(searchRequest, pageRequest);

    console.log(response.data.content);
    return (
        <div className="flex flex-col gap-y-5">
            <GrammarSearchBox />
            <GrammarList page={response.data} />
        </div>
    );
};

export default GrammarPage;
