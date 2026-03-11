import React from "react";
import GrammarSearchBox from "@/features/grammar/components/grammar.search.box";
import GrammarList from "@/features/grammar/components/grammar.list";

const Grammar = () => {
    return (
        <div className="flex flex-col gap-y-5">
            <GrammarSearchBox />
            <GrammarList />
        </div>
    );
};

export default Grammar;
