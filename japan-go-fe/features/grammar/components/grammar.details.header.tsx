import React from "react";
import WrapBox from "@/components/ui/wrap.box";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useTranslations } from "next-intl";
import { Link } from "@/i18n/navigation";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import { slugifyText } from "@/utils/slugify.text";
import { Divider } from "@mui/material";
import BackIcon from "@/components/ui/icons/back.icon";

const GrammarDetailsHeader = ({ grammar }: { grammar: GrammarResponse }) => {
    const t = useTranslations();
    return (
        <WrapBox>
            <div className="flex items-center justify-between">
                <div className="flex items-center gap-x-3">
                    <TooltipCustom title={t("Pages.grammar.backToGrammarPage")}>
                        <Link href={"/grammar"}>
                            <IconButtonCustom>
                                <BackIcon />
                            </IconButtonCustom>
                        </Link>
                    </TooltipCustom>

                    <Divider orientation="vertical" flexItem />

                    <TooltipCustom
                        title={t("Pages.grammar.goToGrammarLessonPage")}
                        placement="right"
                    >
                        <Link
                            href={{
                                pathname: "/lesson/grammar/[slug]",
                                params: {
                                    slug: slugifyText(
                                        grammar.lessonName +
                                            "-" +
                                            grammar.lessonId,
                                    ),
                                },
                            }}
                            className="hover:text-tc-highlight text-lg font-semibold hover:underline"
                        >
                            {grammar.lessonName}
                        </Link>
                    </TooltipCustom>
                </div>
                <h1 className="text-lg font-semibold">{grammar.bookTitle}</h1>
            </div>
        </WrapBox>
    );
};

export default GrammarDetailsHeader;
