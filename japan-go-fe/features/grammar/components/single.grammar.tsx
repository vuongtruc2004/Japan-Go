import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";
import { Divider } from "@mui/material";

const SingleGrammar = ({
    grammar,
    index,
}: {
    grammar: GrammarResponse;
    index: number;
}) => {
    const t = useTranslations();
    return (
        <Link
            href={{
                pathname: "/grammar/[slug]",
                params: {
                    slug: slugifyText(grammar.grammarTitle + "-" + grammar.id),
                },
            }}
            className="group hover:bg-hbgc-highlight bg-bgc-page border-bdc-primary flex items-center gap-x-3 rounded-md border p-2 transition-all duration-150"
        >
            <div className="bg-bgc-highlight text-tc-pure flex h-10 w-10 items-center justify-center rounded-md">
                {index}
            </div>

            <div>
                <p className="font-semibold text-nowrap">
                    {t("Pages.grammar.grammarJlpt", {
                        jlptLevel: grammar.jlptLevel,
                    })}
                </p>
                <p className="text-tc-muted text-sm font-semibold text-nowrap">
                    {grammar.bookTitle}
                </p>
            </div>

            <Divider orientation="vertical" variant="middle" flexItem />

            <p className="group-hover:underline">
                {grammar.grammarTitle}：{grammar.translation}
            </p>
        </Link>
    );
};

export default SingleGrammar;
