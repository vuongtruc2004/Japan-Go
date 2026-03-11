import React from "react";
import { Metadata } from "next";
import { getGrammarDetailsById } from "@/services/grammar.service";

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
    return <div>{response.data.grammarTitle}</div>;
};

export default GrammarDetailsPage;
