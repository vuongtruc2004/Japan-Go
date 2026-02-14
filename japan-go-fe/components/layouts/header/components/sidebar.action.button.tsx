import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import DehazeOutlinedIcon from "@mui/icons-material/DehazeOutlined";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import { useSidebarCollapse } from "../../sidebar/context/sidebar.collapse";

const SidebarActionButton = () => {
    const { setIsCollapse, isCollapse } = useSidebarCollapse();
    const t = useTranslations("Common");

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
