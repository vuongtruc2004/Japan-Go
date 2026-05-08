"use client";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import DrawOutlinedIcon from "@mui/icons-material/DrawOutlined";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import WrapBox from "@/components/ui/wrap.box";
import { useRouter, useSearchParams } from "next/navigation";
import React from "react";

const KanjiSearchBox = () => {
    const t = useTranslations();
    const searchParams = useSearchParams();
    const { push } = useRouter();

    const currentSearchKey = searchParams.get("searchKey") || "";

    const handleSubmit = (e: React.SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();

        const formData = new FormData(e.currentTarget);
        const value = formData.get("searchKey");
        const searchKey = typeof value === "string" ? value.trim() : "";

        const newSearchParams = new URLSearchParams(searchParams.toString());
        if (searchKey) {
            newSearchParams.set("searchKey", searchKey);
        } else {
            newSearchParams.delete("searchKey");
        }

        push(`/kanji/details?${newSearchParams}`);
    };

    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">{t("Common.search")}</h1>
            <form
                onSubmit={handleSubmit}
                className="flex items-center justify-between gap-x-3"
            >
                <TextFieldCustom
                    name="searchKey"
                    size="small"
                    variant="outlined"
                    placeholder={t("Pages.kanji.search.placeholder")}
                    fullWidth
                    defaultValue={currentSearchKey}
                />

                <div className="flex items-center gap-x-3">
                    <TooltipCustom title={t("Pages.kanji.search.drawButton")}>
                        <Button
                            variant="outlined"
                            color="success"
                            sx={{ minWidth: "40px", width: "40px" }}
                        >
                            <DrawOutlinedIcon fontSize="medium" />
                        </Button>
                    </TooltipCustom>

                    <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        sx={{ textWrap: "nowrap" }}
                    >
                        {t("Common.search")}
                    </Button>
                </div>
            </form>
        </WrapBox>
    );
};

export default KanjiSearchBox;
