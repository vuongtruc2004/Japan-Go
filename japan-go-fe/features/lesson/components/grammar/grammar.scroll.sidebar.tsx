"use client";
import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import GrammarScrollLink from "@/features/lesson/components/grammar/grammar.scroll.link";

const GrammarScrollSidebar = ({
    grammars,
}: {
    grammars: GrammarResponse[];
}) => {
    const t = useTranslations();

    return (
        <div className="bg-bgc-app border-bdc-primary sticky top-21.25 flex h-[calc(100vh-105px)] w-80 shrink-0 flex-col gap-y-3 overflow-y-auto rounded-md border p-5">
            {grammars.map((grammar) => {
                return (
                    <div key={grammar.id}>
                        <GrammarScrollLink
                            targetId={`grammar-${grammar.id}`}
                            text={grammar.grammarTitle}
                            isSemibold={true}
                        />

                        <div className="mt-3 flex flex-col items-start px-3">
                            <GrammarScrollLink
                                targetId={`grammar-meaning-${grammar.grammarMeaning.id}`}
                                text={`${t("Pages.lesson.grammar.grammarMeaning")}:`}
                            />
                            <GrammarScrollLink
                                targetId={`grammar-structure-${grammar.grammarStructure.id}`}
                                text={`${t("Pages.lesson.grammar.grammarStructure")}:`}
                            />
                            <GrammarScrollLink
                                targetId={`grammar-example-${grammar.grammarExample.id}`}
                                text={`${t("Pages.lesson.grammar.grammarExample")}:`}
                            />
                            <GrammarScrollLink
                                targetId={`grammar-note-${grammar.grammarNote.id}`}
                                text={`${t("Pages.lesson.grammar.grammarNote")}:`}
                            />
                        </div>
                    </div>
                );
            })}
        </div>
    );
};

export default GrammarScrollSidebar;
