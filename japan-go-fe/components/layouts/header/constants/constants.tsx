import { AppLocales } from "@/types/app/i18n.type";
import EmojiEventsOutlinedIcon from "@mui/icons-material/EmojiEventsOutlined";
import SettingsOutlinedIcon from "@mui/icons-material/SettingsOutlined";
import { AccountMenuItem } from "../types/ui.type";

export const LOCALES_META: Record<AppLocales, { flag: string }> = {
    vi: {
        flag: "vn",
    },
    // en: {
    //     flag: "sh",
    // },
    // ja: {
    //     flag: "jp",
    // },
};

export const ACCOUNT_MENU_ITEMS: AccountMenuItem[] = [
    {
        id: "item-1",
        icon: <EmojiEventsOutlinedIcon />,
        link: "/achievement",
        linkKey: "achievement",
    },
    {
        id: "item-2",
        icon: <SettingsOutlinedIcon />,
        link: "/settings",
        linkKey: "settings",
    },
];

