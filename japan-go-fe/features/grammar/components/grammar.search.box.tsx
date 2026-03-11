import React from "react";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import { Button } from "@mui/material";
import WrapBox from "@/components/ui/wrap.box";

const GrammarSearchBox = () => {
    const t = useTranslations();

    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">
                {t("Pages.grammar.searchTitle")}
            </h1>

            <form
                action=""
                className="flex items-center justify-between gap-x-3"
            >
                <TextFieldCustom
                    name="kanji-search"
                    size="small"
                    variant="outlined"
                    placeholder={t("Pages.grammar.searchPlaceholder")}
                    fullWidth
                />

                <Button
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
