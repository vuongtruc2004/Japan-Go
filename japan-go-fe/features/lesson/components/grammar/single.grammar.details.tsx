import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import { DecoratorClassName } from "@/types/enums/share.enum";
import GrammarMeaning from "@/features/lesson/components/grammar/grammar.meaning";
import GrammarStructure from "@/features/lesson/components/grammar/grammar.structure";
import GrammarExample from "@/features/lesson/components/grammar/grammar.example";
import GrammarNote from "@/features/lesson/components/grammar/grammar.note";

const SingleGrammarDetails = ({ grammar }: { grammar: GrammarResponse }) => {
    return (
        <>
            <h1
                id={`grammar-${grammar.id}`}
                className={`mb-3 max-w-max rounded-md border px-4 text-xl font-semibold ${DecoratorClassName.GREEN}`}
            >
                {grammar.grammarTitle}：{grammar.translation}
            </h1>

            <div className="flex flex-col gap-y-3">
                <GrammarMeaning grammarMeaning={grammar.grammarMeaning} />
                <GrammarStructure grammarStructure={grammar.grammarStructure} />
                <GrammarExample grammarExample={grammar.grammarExample} />
                <GrammarNote grammarNote={grammar.grammarNote} />
            </div>
        </>
    );
};

export default SingleGrammarDetails;
