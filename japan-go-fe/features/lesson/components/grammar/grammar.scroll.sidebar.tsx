"use client";
import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import GrammarScrollLink from "@/features/lesson/components/grammar/grammar.scroll.link";
import { Divider } from "@mui/material";

const GrammarScrollSidebar = ({
    grammars,
}: {
    grammars: GrammarResponse[];
}) => {
    return (
        <div className="bg-bgc-app border-bdc-primary sticky top-21.25 flex h-[calc(100vh-105px)] w-75 shrink-0 flex-col gap-y-3 overflow-y-auto rounded-md border p-5">
            {grammars.map((grammar, index) => {
                return (
                    <div key={grammar.id}>
                        <GrammarScrollLink
                            targetId={`grammar-${grammar.id}`}
                            text={
                                grammar.grammarTitle +
                                "：" +
                                grammar.translation
                            }
                            isSemibold={true}
                            index={index}
                        />
                        {index !== grammars.length - 1 && (
                            <Divider sx={{ my: 1 }} />
                        )}
                    </div>
                );
            })}
        </div>
    );
};

export default GrammarScrollSidebar;
