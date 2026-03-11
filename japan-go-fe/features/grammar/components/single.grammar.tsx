import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";

const SingleGrammar = ({ grammar }: { grammar: GrammarResponse }) => {
    const t = useTranslations();
    return (
        <Link
            href={{
                pathname: "/grammar/[slug]",
                query: {
                    slug: slugifyText(grammar.grammarTitle + "-" + grammar.id),
                },
            }}
            className="group hover:bg-hbgc-highlight bg-bgc-page border-bdc-primary flex items-center gap-x-3 rounded-md border px-5 py-3 transition-all duration-150"
        >
            <p className="font-semibold">
                {t("Pages.grammar.grammarJlpt", {
                    jlptLevel: grammar.jlptLevel,
                })}
            </p>
            <p className="group-hover:underline">
                {grammar.grammarTitle}：{grammar.translation}
            </p>
        </Link>
    );
};

export default SingleGrammar;
