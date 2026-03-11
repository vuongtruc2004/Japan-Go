import React from "react";
import { Metadata } from "next";
import { getGrammarDetailsById } from "@/services/grammar.service";
import SingleGrammarDetails from "@/features/lesson/components/grammar/single.grammar.details";
import WrapBox from "@/components/ui/wrap.box";

const getGrammarDetailsBFromParams = async (
    params: Promise<{ slug: string }>,
) => {
    const { slug } = await params;
    const id = slug.split("-").pop();
    if (!id) {
        throw new Error("invalidPath");
    }
    return getGrammarDetailsById(id);
};

export async function generateMetadata({
    params,
}: {
    params: Promise<{ slug: string }>;
}): Promise<Metadata> {
    const response = await getGrammarDetailsBFromParams(params);
    return {
        title: response.data.grammarTitle,
    };
}

const GrammarDetailsPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const response = await getGrammarDetailsBFromParams(params);
    return (
        <WrapBox>
            <SingleGrammarDetails grammar={response.data} />
        </WrapBox>
    );
};

export default GrammarDetailsPage;
