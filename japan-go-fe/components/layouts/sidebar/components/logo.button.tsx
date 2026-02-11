import { Link } from "@/i18n/navigation";
import { useTranslations } from "next-intl";
import Image from "next/image";
import { useSidebarCollapse } from "../context/sidebar.collapse";

const LogoButton = () => {
    const { isCollapse } = useSidebarCollapse();
    const t = useTranslations("AppSidebar");
    return (
        <div className="flex items-center justify-center py-3.5">
            <Link
                href={"/home"}
                className="flex items-center gap-x-3 select-none"
            >
                <Image
                    src={"/logo.png"}
                    alt="app-logo"
                    width={36}
                    height={36}
                />
                {!isCollapse && (
                    <h1 className="font-bold whitespace-nowrap">
                        {t("projectName")}
                    </h1>
                )}
            </Link>
        </div>
    );
};

export default LogoButton;

