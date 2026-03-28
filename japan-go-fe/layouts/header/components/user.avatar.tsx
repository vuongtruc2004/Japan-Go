import { Avatar } from "@mui/material";
import { useState } from "react";
import AccountMenu from "./account.menu";

const UserAvatar = () => {
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    return (
        <>
            <button
                className="border-l-bdc-muted ml-3 cursor-pointer"
                onClick={(event) => setAnchorEl(event.currentTarget)}
            >
                <Avatar
                    sx={{
                        width: "40px",
                        height: "40px",
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
