import React from "react";
import { GrammarMeaningResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import { DecoratorClassName } from "@/types/enums/share.enum";
import CircleNumber from "@/components/ui/circle.number";

const GrammarMeaning = ({
    grammarMeaning,
}: {
    grammarMeaning: GrammarMeaningResponse;
}) => {
    const t = useTranslations();
    return (
        <div>
            <h1
                className={`mb-3 w-max rounded-md border px-4 text-lg font-semibold ${DecoratorClassName.BLUE}`}
            >
                {t("Pages.lesson.grammar.grammarMeaning")}:
            </h1>
            <div className="flex flex-col gap-y-3">
                {grammarMeaning.sentences.map((sentence, index) => {
                    return (
                        <div key={sentence.id} className="px-3">
                            <p className="font-noto-sans-jp flex items-center gap-x-2">
                                <CircleNumber num={index + 1} />
                                <span
                                    className="font-semibold whitespace-pre-line"
                                    dangerouslySetInnerHTML={{
                                        __html: sentence.japaneseRaw,
                                    }}
                                />
                            </p>
                            <p className="mt-1 flex items-center gap-x-1">
                                <span>⇒</span>
                                <span
                                    className="whitespace-pre-line"
                                    dangerouslySetInnerHTML={{
                                        __html: sentence.vietnameseRaw,
                                    }}
                                />
                            </p>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default GrammarMeaning;
