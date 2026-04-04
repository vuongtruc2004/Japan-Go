import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import WrapBox from "@/components/ui/wrap.box";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import SingleGrammarDetails from "@/features/lesson/components/grammar/single.grammar.details";

const GrammarDetails = ({ grammars }: { grammars: GrammarResponse[] }) => {
    const t = useTranslations();

    if (!grammars.length)
        return (
            <WrapBox>
                <Empty text={t("Pages.lesson.grammar.noGrammars")} />
            </WrapBox>
        );
    return (
        <>
            {grammars.map((grammar, index) => {
                return (
                    <SingleGrammarDetails
                        grammar={grammar}
                        index={index}
                        key={grammar.id}
                    />
                );
            })}
        </>
    );
};

export default GrammarDetails;
