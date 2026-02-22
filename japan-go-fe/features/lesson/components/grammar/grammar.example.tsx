import React from "react";
import { GrammarExampleResponse } from "@/types/api/responses/grammar.response";
import { useTranslations } from "next-intl";
import { DecoratorClassName } from "@/types/enums/share.enum";
import CircleNumber from "@/components/ui/circle.number";

const GrammarExample = ({
    grammarExample,
}: {
    grammarExample: GrammarExampleResponse;
}) => {
    const t = useTranslations();
    return (
        <div>
            <h1
                className={`mb-3 w-max rounded-md border px-4 text-lg font-semibold ${DecoratorClassName.ORANGE}`}
            >
                {t("Pages.lesson.grammar.grammarExample")}:
            </h1>
            <div className="flex flex-col gap-y-3">
                {grammarExample.sentences.map((sentence, index) => {
                    return (
                        <div key={sentence.id} className="px-3">
                            <p className="font-noto-sans-jp flex items-center gap-x-2">
                                <CircleNumber num={index + 1} />
                                <span
                                    className={`font-semibold whitespace-pre-line [&_.grammar-highlight]:rounded [&_.grammar-highlight]:border [&_.grammar-highlight]:border-fuchsia-500/20 [&_.grammar-highlight]:bg-fuchsia-500/10 [&_.grammar-highlight]:px-1 [&_.grammar-highlight]:text-fuchsia-700 [&_.grammar-highlight]:hover:border-fuchsia-500 [&_.grammar-highlight]:dark:text-fuchsia-500`}
                                    dangerouslySetInnerHTML={{
                                        __html: sentence.japaneseHtml,
                                    }}
                                />
                            </p>
                            <p className="mt-1 flex items-center gap-x-1 whitespace-pre-line">
                                <span>⇒</span>
                                <span
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

export default GrammarExample;
