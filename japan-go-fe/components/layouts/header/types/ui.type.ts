import { AppLinkKeys, StaticRoutes } from "@/types/app/i18n.type";

export type AccountMenuItem = {
    id: string;
    linkKey: AppLinkKeys;
    icon: React.ReactNode;
    link: StaticRoutes;
    hasBadge?: boolean;
}

