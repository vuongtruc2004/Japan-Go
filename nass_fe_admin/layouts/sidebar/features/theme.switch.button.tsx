"use client";
import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import { useSidebarCollapse } from "@/libs/wrapper/context/sidebar-collapse/sidebar.collapse";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import { useColorScheme } from "@mui/material";
import { useTranslations } from "next-intl";

const ThemeSwitchButton = () => {
    const { isCollapse } = useSidebarCollapse();
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
        <TooltipCustom
            title={
                isCollapse
                    ? mode === "light"
                        ? t("darkMode")
                        : t("lightMode")
                    : ""
            }
            placement="right"
            arrow
        >
            <div
                onClick={handleChangeMode}
                className={`text-tc-muted hover:text-tc-highlight hover:bg-hbgc-app ${isCollapse ? "w-10" : "w-[200px]"} flex h-10 cursor-pointer items-center overflow-hidden rounded-md pl-2 transition-all duration-150`}
            >
                <span className="flex h-10 items-center justify-center">
                    {mode === "light" ? (
                        <DarkModeOutlinedIcon />
                    ) : (
                        <LightModeOutlinedIcon />
                    )}
                </span>
                <p
                    className={`text-sm font-semibold whitespace-nowrap ${isCollapse && "hidden"} ml-4`}
                >
                    {mode === "light" ? t("darkMode") : t("lightMode")}
                </p>
            </div>
        </TooltipCustom>
    );
};

export default ThemeSwitchButton;
