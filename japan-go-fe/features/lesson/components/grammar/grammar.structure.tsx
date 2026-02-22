import React from "react";
import { DecoratorClassName } from "@/types/enums/share.enum";
import { GrammarStructureResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import CircleNumber from "@/components/ui/circle.number";

const GrammarStructure = ({
    grammarStructure,
}: {
    grammarStructure: GrammarStructureResponse;
}) => {
    const t = useTranslations();

    return (
        <div>
            <h1
                id={`grammar-structure-${grammarStructure.id}`}
                className={`mb-3 w-max rounded-md border px-4 text-lg font-semibold ${DecoratorClassName.YELLOW}`}
            >
                {t("Pages.lesson.grammar.grammarStructure")}:
            </h1>
            <div className="flex flex-col gap-y-3">
                {grammarStructure.sentences.map((sentence, index) => {
                    return (
                        <p
                            className="font-noto-sans-jp flex items-center gap-x-2 px-3"
                            key={sentence.id}
                        >
                            <CircleNumber num={index + 1} />
                            <span
                                className="font-semibold whitespace-pre-line"
                                dangerouslySetInnerHTML={{
                                    __html: sentence.japaneseRaw,
                                }}
                            />
                        </p>
                    );
                })}
            </div>
        </div>
    );
};

export default GrammarStructure;
