"use client";
import React from "react";
import { KanjiResponse } from "@/types/api/responses/kanji.response";
import KanjiVgAnimator from "@/features/lesson/components/kanji/kanji.vg.animator";
import UpdateMainSinoVietnameseButton from "@/features/kanji-details/components/update.main.sino.vietnamese.button";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import BackIcon from "@/components/ui/icons/back.icon";
import { useTranslations } from "next-intl";
import { useRouter } from "@/i18n/navigation";

const KanjiDetails = ({ kanji }: { kanji: KanjiResponse }) => {
    const t = useTranslations();
    const { push } = useRouter();
    return (
        <div>
            <TooltipCustom title={t("Pages.kanjiDetails.backToKanjiPage")}>
                <IconButtonCustom onClick={() => push("/kanji")}>
                    <BackIcon />
                </IconButtonCustom>
            </TooltipCustom>

            <div className="flex items-center justify-center gap-x-3">
                <div className="aspect-square w-57">
                    <KanjiVgAnimator kanjiVg={kanji.kanjiVg} />
                </div>
                <div className="border-bdc-primary flex aspect-square w-57 items-center justify-center rounded-md border">
                    <p className="text-9xl">{kanji.kanjiCharacter}</p>
                </div>
                <div className="border-bdc-primary relative flex aspect-square w-57 items-center justify-center rounded-md border">
                    <p className="text-6xl">{kanji.mainSinoVietnamese}</p>
                    <UpdateMainSinoVietnameseButton kanji={kanji} />
                </div>
            </div>
        </div>
    );
};

export default KanjiDetails;
