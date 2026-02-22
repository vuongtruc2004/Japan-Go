import { AppNameLinkKeys, StaticRoutes } from "@/types/app/i18n.type";
import React from "react";

export type SidebarItem = {
    id: string;
    nameLinkKey: AppNameLinkKeys;
    activeLinks: StaticRoutes[];
    redirectLink: StaticRoutes;
    icon: React.ReactNode;
    hasBadge?: boolean;
};

export enum SidebarEffectClassName {
    ACTIVE = 'bg-bgc-highlight text-white shadow-md"',
    MUTED = "text-tc-muted hover:text-tc-highlight hover:bg-hbgc-app",
}
