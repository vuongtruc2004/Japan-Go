import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import { useSidebarCollapse } from "@/libs/wrapper/context/sidebar.collapse.wrapper";
import DehazeOutlinedIcon from "@mui/icons-material/DehazeOutlined";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import { useEffect } from "react";

const SidebarActionButton = () => {
    const { setIsCollapse, isCollapse } = useSidebarCollapse();
    const t = useTranslations("AppHeader.sidebarActionButton");

    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            const key = e.key.toLowerCase();

            const target = e.target as HTMLElement | null;
            const isTyping =
                target?.tagName === "INPUT" ||
                target?.tagName === "TEXTAREA" ||
                target?.isContentEditable;

            if (isTyping) return;

            if ((e.ctrlKey || e.metaKey) && key === "b") {
                e.preventDefault();
                setIsCollapse((prev) => !prev);
            }
        };

        window.addEventListener("keydown", handleKeyDown);
        return () => window.removeEventListener("keydown", handleKeyDown);
    }, [setIsCollapse]);

    return (
        <TooltipCustom title={isCollapse ? t("open") : t("close")}>
            <Button
                variant="text"
                color="success"
                onClick={() => setIsCollapse((prev) => !prev)}
                sx={{ minWidth: "40px", width: "40px" }}
            >
                <DehazeOutlinedIcon />
            </Button>
        </TooltipCustom>
    );
};

export default SidebarActionButton;
