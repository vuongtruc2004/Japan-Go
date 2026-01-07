import HomeIcon from "@/components/icons/home.icon";
import AutoStoriesOutlinedIcon from "@mui/icons-material/AutoStoriesOutlined";
import SettingsOutlinedIcon from "@mui/icons-material/SettingsOutlined";
import TranslateIcon from "@mui/icons-material/Translate";
import LogoutButton from "../features/logout.button";
import ThemeSwitchButton from "../features/theme.switch.button";
import { ISidebarGroup } from "./sidebar.type";

export const SIDEBAR_GROUPS: ISidebarGroup[] = [
    {
        id: "g-1",
        groupKeyTranslation: "main",
        items: [
            {
                type: "link",
                id: "e-1",
                linkKeyTranslation: "home",
                icon: <HomeIcon />,
                link: "/home",
            },
            {
                type: "link",
                id: "e-2",
                linkKeyTranslation: "course",
                icon: <AutoStoriesOutlinedIcon />,
                link: "/course",
            },
            {
                type: "link",
                id: "e-3",
                linkKeyTranslation: "kanji",
                icon: <TranslateIcon />,
                link: "/kanji",
            },
        ],
        isLastGroup: false,
    },
    {
        id: "g-2",
        groupKeyTranslation: "sub",
        items: [
            {
                type: "link",
                id: "e-4",
                linkKeyTranslation: "settings",
                icon: <SettingsOutlinedIcon />,
                link: "/settings",
            },
            {
                type: "slot",
                id: "e-5",
                render: <ThemeSwitchButton />,
            },
            {
                type: "slot",
                id: "e-6",
                render: <LogoutButton />,
            },
        ],
        isLastGroup: true,
    },
];
