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
import SinoVietnameseList from "@/features/kanji-details/components/sino.vietnamese.list";
import { Divider } from "@mui/material";

const KanjiDetails = ({ kanji }: { kanji: KanjiResponse }) => {
    const t = useTranslations();
    const { push } = useRouter();
    return (
        <div className="mx-auto max-w-3xl px-6 py-6">
            <div className="mb-8">
                <TooltipCustom title={t("Pages.kanjiDetails.backToKanjiPage")}>
                    <IconButtonCustom onClick={() => push("/kanji")}>
                        <BackIcon />
                    </IconButtonCustom>
                </TooltipCustom>
            </div>

            <div className="mb-8 grid grid-cols-3 gap-x-3">
                <KanjiVgAnimator kanjiVg={kanji.kanjiVg} />
                <div className="border-bdc-primary flex aspect-square shrink-0 items-center justify-center rounded-xl border">
                    <p className="text-9xl">{kanji.kanjiCharacter}</p>
                </div>
                <div className="border-bdc-primary relative flex aspect-square shrink-0 items-center justify-center rounded-xl border">
                    <p className="text-6xl">{kanji.mainSinoVietnamese}</p>
                    <UpdateMainSinoVietnameseButton kanji={kanji} />
                </div>
            </div>

            <Divider className="mb-6" />

            <SinoVietnameseList kanji={kanji} />
        </div>
    );
};

export default KanjiDetails;
