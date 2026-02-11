import { Avatar } from "@mui/material";
import { useState } from "react";
import AccountMenu from "./account.menu";

const UserAvatar = () => {
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    return (
        <>
            <button
                className="border-l-bdc-muted ml-3 cursor-pointer"
                onClick={handleClick}
            >
                <Avatar
                    sx={{
                        width: "36px",
                        height: "36px",
                    }}
                >
                    T
                </Avatar>
            </button>

            <AccountMenu anchorEl={anchorEl} setAnchorEl={setAnchorEl} />
        </>
    );
};

export default UserAvatar;

