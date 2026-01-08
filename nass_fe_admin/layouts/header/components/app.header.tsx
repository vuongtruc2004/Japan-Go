"use client";
import { Link } from "@/i18n/navigation";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import AppSearchBox from "../features/app.search.box";
import SidebarActionButton from "../features/sidebar.action.button";
import SwitchLanguageSelect from "../features/switch.language.select";

const AppHeader = () => {
    const t = useTranslations("AppHeader");
    return (
        <div className="border-b-bdc-primary bg-bgc-app sticky top-0 left-0 z-10 flex items-center justify-between border-b px-3 py-3.5">
            <div className="flex items-center gap-x-3">
                <SidebarActionButton />
                <AppSearchBox />
            </div>

            <div className="flex items-center gap-x-3">
                <SwitchLanguageSelect />
                <Link href={"/register"}>
                    <Button variant="outlined" color="success">
                        {t("registerButton")}
                    </Button>
                </Link>

                <Link href={"/login"}>
                    <Button variant="contained">{t("loginButton")}</Button>
                </Link>
            </div>
        </div>
    );
};

export default AppHeader;
