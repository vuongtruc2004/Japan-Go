import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import { useSidebarCollapse } from "@/libs/wrapper/context/sidebar.collapse.wrapper";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import { useTranslations } from "next-intl";

const LogoutButton = () => {
    const { isCollapse } = useSidebarCollapse();
    const t = useTranslations("AppSidebar.buttons");
    const handleLogout = () => {
        console.log("Logout!");
    };
    return (
        <TooltipCustom
            title={isCollapse ? t("logoutButton") : ""}
            placement="right"
            arrow
        >
            <div
                onClick={handleLogout}
                className={`text-tc-muted hover:text-tc-error hover:bg-hbgc-app ${isCollapse ? "w-10" : "w-[200px]"} flex h-10 cursor-pointer items-center overflow-hidden rounded-md pl-2 transition-all duration-150`}
            >
                <span className="flex h-10 items-center justify-center">
                    <LogoutOutlinedIcon />
                </span>
                <p
                    className={`text-sm font-semibold whitespace-nowrap ${isCollapse && "hidden"} ml-4`}
                >
                    {t("logoutButton")}
                </p>
            </div>
        </TooltipCustom>
    );
};

export default LogoutButton;
