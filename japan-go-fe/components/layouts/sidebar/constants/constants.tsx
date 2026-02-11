import FolderOpenedIcon from "@/components/ui/icons/folder.opened.icon";
import HomeIcon from "@/components/ui/icons/home.icon";
import TranslateIcon from "@mui/icons-material/Translate";
import { SidebarItem } from "../types/ui.type";

export const SIDEBAR_ITEMS: SidebarItem[] = [
    {
        id: "e-1",
        linkKey: "home",
        icon: <HomeIcon />,
        link: "/home",
    },
    {
        id: "e-2",
        linkKey: "yourLibrary",
        icon: <FolderOpenedIcon />,
        link: "/your-library",
    },
    {
        id: "e-3",
        linkKey: "kanji",
        icon: <TranslateIcon />,
        link: "/kanji",
    },
];

