import { AppLinkKeys, StaticRoutes } from "@/types/app/i18n.type";
import React from "react";

export type SidebarItem = {
    id: string;
    linkKey: AppLinkKeys;
    icon: React.ReactNode;
    link: StaticRoutes;
    hasBadge?: boolean;
};
