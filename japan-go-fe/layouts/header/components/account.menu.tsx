import { Link } from "@/i18n/navigation";
import { Avatar, Divider, Popover } from "@mui/material";
import { useTranslations } from "next-intl";
import { Dispatch, SetStateAction } from "react";
import { ACCOUNT_MENU_ITEMS } from "../constants/header.constants";
import LogoutButton from "./logout.button";
import ThemeSwitchButton from "./theme.switch.button";

const AccountMenu = ({
    anchorEl,
    setAnchorEl,
}: {
    anchorEl: HTMLButtonElement | null;
    setAnchorEl: Dispatch<SetStateAction<HTMLButtonElement | null>>;
}) => {
    const t = useTranslations("Common.links");

    return (
        <Popover
            open={Boolean(anchorEl)}
            anchorEl={anchorEl}
            onClose={() => setAnchorEl(null)}
            anchorOrigin={{
                vertical: "bottom",
                horizontal: "right",
            }}
            transformOrigin={{
                vertical: "top",
                horizontal: "right",
            }}
        >
            <div>
                <div className="flex items-center gap-x-3 p-3.5">
                    <Avatar
                        sx={{
                            width: "56px",
                            height: "56px",
                        }}
                    >
                        T
                    </Avatar>
                    <div className="text-left">
                        <h2 className="text-sm font-semibold">
                            {"Nguyen Truc"}
                        </h2>
                        <p className="text-tc-muted text-sm font-semibold">
                            {"vuongtruc2004@gmail.com"}
                        </p>
                    </div>
                </div>

                <Divider />

                {ACCOUNT_MENU_ITEMS.map((item) => {
                    return (
                        <Link
                            key={item.id}
                            href={item.link}
                            className="hover:bg-hbgc-app flex w-full min-w-62.5 items-center gap-x-3 px-3.5 py-3 transition-all duration-150"
                        >
                            {item.icon}
                            <p className="text-sm font-semibold whitespace-nowrap">
                                {t(item.linkKey)}
                            </p>
                        </Link>
                    );
                })}

                <ThemeSwitchButton />

                <Divider />

                <LogoutButton />
            </div>
        </Popover>
    );
};

export default AccountMenu;
