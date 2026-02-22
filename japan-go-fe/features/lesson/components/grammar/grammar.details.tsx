import React from "react";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import WrapBox from "@/components/ui/wrap.box";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import GrammarMeaning from "@/features/lesson/components/grammar/grammar.meaning";
import { DecoratorClassName } from "@/types/enums/share.enum";
import GrammarStructure from "@/features/lesson/components/grammar/grammar.structure";
import GrammarExample from "@/features/lesson/components/grammar/grammar.example";
import GrammarNote from "@/features/lesson/components/grammar/grammar.note";
import { Divider } from "@mui/material";

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
            <>
                {grammars.map((grammar, index) => {
                    return (
                        <div key={grammar.id}>
                            <div>
                                <h1
                                    className={`mb-3 rounded-md border px-4 text-xl font-semibold ${DecoratorClassName.GREEN}`}
                                >
                                    {grammar.grammarTitle}
                                </h1>
                                <div className="flex flex-col gap-y-3">
                                    <GrammarMeaning
                                        grammarMeaning={grammar.grammarMeaning}
                                    />
                                    <GrammarStructure
                                        grammarStructure={
                                            grammar.grammarStructure
                                        }
                                    />
                                    <GrammarExample
                                        grammarExample={grammar.grammarExample}
                                    />
                                    <GrammarNote
                                        grammarNote={grammar.grammarNote}
                                    />
                                </div>
                            </div>
                            {index !== grammars.length - 1 && (
                                <Divider sx={{ my: 3 }} />
                            )}
                        </div>
                    );
                })}
            </>
        </WrapBox>
    );
};

export default GrammarDetails;
