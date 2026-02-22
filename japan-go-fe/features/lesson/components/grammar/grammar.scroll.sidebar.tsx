"use client";
import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import GrammarScrollLink from "@/features/lesson/components/grammar/grammar.scroll.link";
import { useInViewGrammar } from "@/features/lesson/hooks/use.in.view.grammar";

const GrammarScrollSidebar = ({
    grammars,
}: {
    grammars: GrammarResponse[];
}) => {
    const t = useTranslations();
    const grammarIds = grammars.map((g) => `grammar-${g.id}`);
    const inViewGrammarId = useInViewGrammar(grammarIds);

    return (
        <div className="group fixed top-1/2 right-5 z-10 -translate-y-1/2">
            <div className="fixed top-1/2 right-0 z-10 flex -translate-y-1/2 cursor-pointer flex-col gap-y-3 px-5">
                {grammars.map((grammar) => {
                    return (
                        <div
                            key={grammar.id}
                            className="flex flex-col items-end gap-y-3"
                        >
                            <span className="bg-bgc-muted h-0.5 w-4 rounded-md" />
                            {Array.from({ length: 4 }).map((_, index) => {
                                return (
                                    <span
                                        key={index}
                                        className="bg-bgc-muted h-0.5 w-3 rounded-md"
                                    />
                                );
                            })}
                        </div>
                    );
                })}
            </div>

            <div className="bg-bgc-app border-bdc-primary invisible fixed top-1/2 right-0 z-20 flex h-[calc(100vh-80px)] w-68 translate-x-6 -translate-y-1/2 flex-col gap-y-3 overflow-y-auto rounded-l-2xl border p-5 opacity-0 shadow-md transition-all duration-300 ease-out group-hover:visible group-hover:translate-x-0 group-hover:opacity-100">
                {grammars.map((grammar) => {
                    return (
                        <div key={grammar.id}>
                            <GrammarScrollLink
                                targetId={`grammar-${grammar.id}`}
                                text={grammar.grammarTitle}
                                isSemibold={true}
                                isInView={
                                    inViewGrammarId === `grammar-${grammar.id}`
                                }
                            />

                            <div className="flex flex-col items-start px-3">
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
        </div>
    );
};

export default GrammarScrollSidebar;
