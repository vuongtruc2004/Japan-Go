import FolderOpenedIcon from "@/components/ui/icons/folder.opened.icon";
import HomeIcon from "@/components/ui/icons/home.icon";
import TranslateIcon from "@mui/icons-material/Translate";
import { SidebarItem } from "../types/sidebar.ui.type";
import GrammarIcon from "@/components/ui/icons/grammar.icon";

export const SIDEBAR_ITEMS: SidebarItem[] = [
    {
        id: "e-1",
        nameLinkKey: "home",
        activeLinks: ["/home"],
        redirectLink: "/home",
        icon: <HomeIcon />,
    },
    {
        id: "e-2",
        nameLinkKey: "yourLibrary",
        activeLinks: [
            "/your-library",
            "/your-library/folder",
            "/your-library/lesson",
        ],
        redirectLink: "/your-library",
        icon: <FolderOpenedIcon />,
    },
    {
        id: "e-3",
        nameLinkKey: "kanji",
        activeLinks: ["/kanji"],
        redirectLink: "/kanji",
        icon: <TranslateIcon />,
    },
    {
        id: "e-4",
        nameLinkKey: "grammar",
        activeLinks: ["/grammar", "/grammar/[slug]"],
        redirectLink: "/grammar",
        icon: <GrammarIcon />,
    },
];
