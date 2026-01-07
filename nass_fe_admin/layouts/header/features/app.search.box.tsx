"use client";
import SearchIcon from "@mui/icons-material/Search";
import { InputAdornment, TextField } from "@mui/material";
import { useTranslations } from "next-intl";

const AppSearchBox = () => {
    const t = useTranslations("AppHeader");

    return (
        <form>
            <TextField
                color="success"
                slotProps={{
                    input: {
                        startAdornment: (
                            <InputAdornment position="start">
                                <SearchIcon color="success" />
                            </InputAdornment>
                        ),
                        sx: {
                            width: "400px",
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
                placeholder={t("appSearchPlaceholder")}
            />
        </form>
    );
};

export default AppSearchBox;
