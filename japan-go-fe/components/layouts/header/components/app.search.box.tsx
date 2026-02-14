"use client";
import SearchIcon from "@mui/icons-material/Search";
import { InputAdornment } from "@mui/material";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";

const AppSearchBox = () => {
    const t = useTranslations("AppHeader");

    return (
        <form>
            <TextFieldCustom
                slotProps={{
                    input: {
                        startAdornment: (
                            <InputAdornment position="start">
                                <SearchIcon color="success" />
                            </InputAdornment>
                        ),
                        sx: {
                            width: "400px",
                        },
                    },
                }}
                size="small"
                variant="outlined"
                placeholder={t("appSearchPlaceholder")}
            />
        </form>
    );
};

export default AppSearchBox;
