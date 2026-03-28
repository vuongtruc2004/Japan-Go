"use client";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Link, usePathname } from "@/i18n/navigation";
import { Badge } from "@mui/material";
import { useTranslations } from "next-intl";
import { useSidebarCollapse } from "../../context/sidebar.collapse";
import { SidebarItem } from "../../types/sidebar.ui.type";
import { getSidebarItemEffectClassNameByItemPaths } from "@/layouts/sidebar/utils/sidebar.utils";

const SidebarSingleItem = ({ item }: { item: SidebarItem }) => {
    const t = useTranslations("Common.links");

    const { isCollapse } = useSidebarCollapse();
    const pathname = usePathname();

    const linkEffectClass = getSidebarItemEffectClassNameByItemPaths(
        pathname,
        item.activeLinks,
    );

    return (
        <TooltipCustom
            title={isCollapse ? t(`${item.nameLinkKey}`) : ""}
            placement="right"
            arrow
        >
            <Link
                href={item.redirectLink}
                className={`${isCollapse ? "w-10" : "w-50"} flex h-10 items-center overflow-hidden rounded-md pl-2 transition-all duration-150 ${linkEffectClass}`}
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
                    {t(`${item.nameLinkKey}`)}
                </p>
            </Link>
        </TooltipCustom>
    );
};

export default SidebarSingleItem;
