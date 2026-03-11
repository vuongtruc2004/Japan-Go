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
    searchParams: {
        searchKey?: string;
        pageNumber?: string;
        pageSize?: string;
    };
}) => {
    const { searchKey = "", pageNumber = "1", pageSize = "10" } = searchParams;

    const searchRequest: GrammarSearchRequest = {
        searchKey,
    };

    const pageRequest: PageRequest = {
        pageNumber,
        pageSize,
    };

    const response = await getAllGrammars(searchRequest, pageRequest);

    return (
        <div className="flex flex-col gap-y-5">
            <GrammarSearchBox />
            <GrammarList grammars={response.data.content} />
        </div>
    );
};

export default GrammarPage;
