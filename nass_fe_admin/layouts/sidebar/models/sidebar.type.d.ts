import { AppLinks, SidebarLinks } from "@/global";

type SidebarItem =
    | {
        id: string;
        linkKeyTranslation: SidebarLinks;
        icon: React.ReactNode;
        link: AppLinks;
        hasBadge?: boolean;
        type: "link";
    }
    | {
        id: string;
        render: React.ReactElement;
        type: "slot";
    };

interface ISidebarGroup {
    id: string;
    groupKeyTranslation: "main" | "sub";
    items: SidebarItem[];
    isLastGroup: boolean;
}