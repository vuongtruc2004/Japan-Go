import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import WrapBox from "@/components/ui/wrap.box";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import { Divider } from "@mui/material";
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
        <WrapBox>
            {grammars.map((grammar, index) => {
                return (
                    <div key={grammar.id}>
                        <SingleGrammarDetails grammar={grammar} index={index} />
                        {index !== grammars.length - 1 && (
                            <Divider sx={{ my: 3 }} />
                        )}
                    </div>
                );
            })}
        </WrapBox>
    );
};

export default GrammarDetails;
