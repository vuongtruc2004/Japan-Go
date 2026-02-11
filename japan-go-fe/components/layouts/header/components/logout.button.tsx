import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import { useTranslations } from "next-intl";

const LogoutButton = () => {
    const t = useTranslations("AppSidebar.buttons");
    const handleLogout = () => {
        console.log("Logout!");
    };
    return (
        <div
            onClick={handleLogout}
            className={`text-tc-error hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150`}
        >
            <LogoutOutlinedIcon />
            <p className={`text-sm font-semibold whitespace-nowrap`}>
                {t("logoutButton")}
            </p>
        </div>
    );
};

export default LogoutButton;

