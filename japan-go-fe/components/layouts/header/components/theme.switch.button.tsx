"use client";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import { useColorScheme } from "@mui/material";
import { useTranslations } from "next-intl";

const ThemeSwitchButton = () => {
    const { mode, setMode } = useColorScheme();
    const t = useTranslations("AppSidebar.buttons.modeButtons");
    if (!mode) {
        return null;
    }

    const handleChangeMode = () => {
        if (mode === "light") setMode("dark");
        if (mode === "dark") setMode("light");
    };

    return (
        <button
            onClick={handleChangeMode}
            className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150"
        >
            {mode === "light" ? (
                <DarkModeOutlinedIcon />
            ) : (
                <LightModeOutlinedIcon />
            )}
            <p className="text-sm font-semibold whitespace-nowrap">
                {mode === "light" ? t("darkMode") : t("lightMode")}
            </p>
        </button>
    );
};

export default ThemeSwitchButton;

