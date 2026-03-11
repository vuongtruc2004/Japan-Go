"use client";
import React from "react";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import { Button } from "@mui/material";
import WrapBox from "@/components/ui/wrap.box";
import { usePathname } from "@/i18n/navigation";
import { useRouter, useSearchParams } from "next/navigation";

const GrammarSearchBox = () => {
    const t = useTranslations();
    const pathname = usePathname();
    const searchParams = useSearchParams();
    const { replace } = useRouter();

    const currentSearchKey = searchParams.get("searchKey") || "";

    const handleSubmit = (e: React.SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();

        const formData = new FormData(e.currentTarget);
        const value = formData.get("searchKey");
        const searchKey = typeof value === "string" ? value.trim() : "";

        const newSearchParams = new URLSearchParams(searchParams.toString());

        if (searchKey) {
            newSearchParams.set("searchKey", searchKey);
            newSearchParams.set("page", "1");
        } else {
            newSearchParams.delete("searchKey");
            newSearchParams.delete("page");
        }

        replace(`${pathname}?${newSearchParams}`);
    };

    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">
                {t("Pages.grammar.searchTitle")}
            </h1>

            <form
                onSubmit={handleSubmit}
                className="flex items-center justify-between gap-x-3"
            >
                <TextFieldCustom
                    name="searchKey"
                    size="small"
                    variant="outlined"
                    placeholder={t("Pages.grammar.searchPlaceholder")}
                    fullWidth
                    defaultValue={currentSearchKey}
                />

                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    sx={{ textWrap: "nowrap" }}
                >
                    {t("Common.search")}
                </Button>
            </form>
        </WrapBox>
    );
};

export default GrammarSearchBox;
