import React from "react";
import { CircularProgress } from "@mui/material";
import { useSidebarCollapse } from "@/layouts/sidebar/context/sidebar.collapse";
import { useTranslations } from "next-intl";

const PinFoldersLoading = () => {
    const t = useTranslations("Common");
    const { isCollapse } = useSidebarCollapse();
    return (
        <div
            className={`${isCollapse ? "w-10" : "w-50"} text-tc-muted flex h-10 items-center overflow-hidden rounded-md pl-2 transition-all duration-150`}
        >
            <span className="flex h-10 items-center justify-center">
                <CircularProgress color="inherit" size={24} />
            </span>
            <p
                className={`text-sm font-semibold whitespace-nowrap ${isCollapse && "hidden"} ml-4`}
            >
                {t("loading")}
            </p>
        </div>
    );
};

export default PinFoldersLoading;
