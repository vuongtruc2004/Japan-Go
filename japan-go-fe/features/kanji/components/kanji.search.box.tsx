import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import DrawOutlinedIcon from "@mui/icons-material/DrawOutlined";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import WrapBox from "@/components/ui/wrap.box";

const KanjiSearchBox = () => {
    const t = useTranslations();
    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">{t("Common.search")}</h1>
            <form
                action=""
                className="flex items-center justify-between gap-x-3"
            >
                <TextFieldCustom
                    name="kanji-search"
                    size="small"
                    variant="outlined"
                    placeholder={t("Pages.kanji.search.placeholder")}
                    fullWidth
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
