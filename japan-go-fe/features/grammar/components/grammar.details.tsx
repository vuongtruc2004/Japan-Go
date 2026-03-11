import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import SingleGrammarDetails from "@/features/lesson/components/grammar/single.grammar.details";

const GrammarDetails = ({ grammar }: { grammar: GrammarResponse }) => {
    return (
        <div>
            <SingleGrammarDetails grammar={grammar} />
        </div>
    );
};

export default GrammarDetails;
