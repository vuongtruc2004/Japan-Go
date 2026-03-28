import { SidebarEffectClassName } from "@/layouts/sidebar/types/sidebar.ui.type";

export const getSidebarItemEffectClassNameByItemPaths = (
    pathname: string,
    itemPaths: string[],
): string => {
    return itemPaths.includes(pathname)
        ? SidebarEffectClassName.ACTIVE
        : SidebarEffectClassName.MUTED;
};

export const getSidebarItemEffectClassNameBySlug = (
    pathname: string,
    itemSlug: string,
    slug?: string,
): string => {
    if (slug === itemSlug && pathname.startsWith("/your-library/folder"))
        return SidebarEffectClassName.ACTIVE;
    return SidebarEffectClassName.MUTED;
};
