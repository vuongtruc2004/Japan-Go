import React from "react";
import WrapBox from "@/components/ui/wrap.box";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useTranslations } from "next-intl";
import { Link } from "@/i18n/navigation";

const GrammarDetailsHeader = () => {
    const t = useTranslations();
    return (
        <WrapBox>
            <Link href={"/grammar"}>
                <TooltipCustom
                    title={t("Pages.grammar.backToGrammarPage")}
                    placement={"right"}
                >
                    <IconButtonCustom>
                        <KeyboardArrowLeftIcon />
                    </IconButtonCustom>
                </TooltipCustom>
            </Link>
        </WrapBox>
    );
};

export default GrammarDetailsHeader;
