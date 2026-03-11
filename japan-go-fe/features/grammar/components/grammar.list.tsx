import React from "react";
import WrapBox from "@/components/ui/wrap.box";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import SingleGrammar from "@/features/grammar/components/single.grammar";

const GrammarList = ({ grammars }: { grammars: GrammarResponse[] }) => {
    return (
        <WrapBox>
            <div className="flex flex-col gap-y-3">
                {grammars.map((grammar) => {
                    return <SingleGrammar grammar={grammar} key={grammar.id} />;
                })}
            </div>
        </WrapBox>
    );
};

export default GrammarList;
