import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import DrawOutlinedIcon from "@mui/icons-material/DrawOutlined";
import { Button, TextField } from "@mui/material";
import { useTranslations } from "next-intl";

const KanjiSearchBox = () => {
    const t = useTranslations("Pages.kanji");
    return (
        <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
            <h1 className="text-tc-primary mb-3 font-semibold">
                {t("search.title")}
            </h1>
            <form
                action=""
                className="flex items-center justify-between gap-x-3"
            >
                <TextField
                    slotProps={{
                        input: {
                            sx: {
                                height: "36px",
                                borderRadius: "6px",
                                fieldset: {
                                    border: "1px solid var(--color-bdc-muted)",
                                },
                                "input::placeholder": {
                                    fontSize: "15.2px",
                                },
                            },
                        },
                    }}
                    size="small"
                    variant="outlined"
                    placeholder={t("search.placeholder")}
                    fullWidth
                />

                <div className="flex items-center gap-x-3">
                    <TooltipCustom title={t("search.drawButton")}>
                        <Button
                            variant="outlined"
                            color="success"
                            sx={{ minWidth: "36px", width: "36px" }}
                        >
                            <DrawOutlinedIcon fontSize="medium" />
                        </Button>
                    </TooltipCustom>

                    <Button
                        variant="contained"
                        color="primary"
                        sx={{
                            textWrap: "nowrap",
                            height: "36px",
                        }}
                    >
                        {t("search.title")}
                    </Button>
                </div>
            </form>
        </div>
    );
};

export default KanjiSearchBox;
