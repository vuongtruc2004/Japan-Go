"use client";
import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Button, Popover } from "@mui/material";
import { useState } from "react";
import FolderDeleteButton from "./folder.delete.button";

const FolderMoreButton = ({ folder }: { folder: FolderResponse }) => {
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.stopPropagation();
        event.preventDefault();
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <TooltipCustom title="Khác">
                <Button
                    variant="text"
                    color="primary"
                    onClick={handleClick}
                    sx={{
                        width: "36px",
                        minWidth: "36px",
                        height: "36px",
                        borderRadius: "50%",
                    }}
                >
                    <MoreHorizIcon />
                </Button>
            </TooltipCustom>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
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
                    <FolderDeleteButton folder={folder} />
                </div>
            </Popover>
        </div>
    );
};

export default FolderMoreButton;
