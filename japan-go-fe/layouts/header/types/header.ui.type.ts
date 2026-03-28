import { AppNameLinkKeys, StaticRoutes } from "@/types/app/i18n.type";
import React from "react";

export type AccountMenuItem = {
    id: string;
    linkKey: AppNameLinkKeys;
    icon: React.ReactNode;
    link: StaticRoutes;
    hasBadge?: boolean;
};
