"use client";
import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import { Link, usePathname } from "@/i18n/navigation";
import { useSidebarCollapse } from "@/libs/wrapper/context/sidebar-collapse/sidebar.collapse.wrapper";
import { Badge } from "@mui/material";
import { useTranslations } from "next-intl";
import { SidebarItem } from "../models/sidebar.type";

const SidebarSingleItem = ({ item }: { item: SidebarItem }) => {
    const { isCollapse } = useSidebarCollapse();
    const pathname = usePathname();
    const t = useTranslations("AppSidebar");

    if (item.type === "slot") return item.render;

    const linkEffectClass = pathname.startsWith(item.link)
        ? `bg-bgc-highlight text-white shadow-md`
        : `text-tc-muted hover:text-tc-highlight hover:bg-hbgc-app`;

    return (
        <TooltipCustom
            title={isCollapse ? t(`links.${item.linkKeyTranslation}`) : ""}
            placement="right"
            arrow
        >
            <Link
                href={item.link}
                className={`${isCollapse ? "w-10" : "w-[200px]"} flex h-10 items-center overflow-hidden rounded-md pl-2 transition-all duration-150 ${linkEffectClass}`}
            >
                {item.hasBadge ? (
                    <span className="mr-4 flex h-10 items-center justify-center">
                        <Badge
                            badgeContent={9}
                            color="error"
                            max={99}
                            overlap="circular"
                            slotProps={{
                                badge: {
                                    sx: {
                                        width: "18px",
                                        height: "18px",
                                        minWidth: "18px",
                                        top: "22%",
                                    },
                                },
                            }}
                        >
                            {item.icon}
                        </Badge>
                    </span>
                ) : (
                    <span className="flex h-10 items-center justify-center">
                        {item.icon}
                    </span>
                )}
                <p
                    className={`text-sm font-semibold whitespace-nowrap ${isCollapse && "hidden"} ml-4`}
                >
                    {t(`links.${item.linkKeyTranslation}`)}
                </p>
            </Link>
        </TooltipCustom>
    );
};

export default SidebarSingleItem;
