"use client";
import { useRouter } from "@/i18n/navigation";
import { sendRequest } from "@/utils/fetch.api";
import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
import WarningAmberIcon from "@mui/icons-material/WarningAmber";
import { Button, Popover } from "@mui/material";
import { useState } from "react";

const FolderDeleteButton = ({ folder }: { folder: FolderResponse }) => {
    const { refresh } = useRouter();
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleDeleteFolder = async () => {
        const response = await sendRequest<ApiResponse<number>>({
            url: `/folder/${folder.id}`,
            method: "DELETE",
        });
        if (response.statusCode === 200) {
            refresh();
        }
    };

    return (
        <div>
            <Button
                onClick={handleClick}
                variant="text"
                color="error"
                sx={{ columnGap: "8px" }}
            >
                <DeleteOutlineIcon fontSize="small" />
                <p>Xóa thư mục</p>
            </Button>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "center",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "center",
                }}
            >
                <div className="w-[250px] px-4 py-2">
                    <div className="mb-3 flex items-start gap-x-3">
                        <WarningAmberIcon color="warning" />
                        <div className="text-sm">
                            <p className="font-semibold">
                                Xóa thư mục: {folder.folderName}
                            </p>

                            <p>Bạn có chắc chắn muốn xóa thư mục này không?</p>
                        </div>
                    </div>
                    <div className="flex items-center justify-end gap-x-2">
                        <Button
                            onClick={handleClose}
                            variant="outlined"
                            color="primary"
                            sx={{
                                width: "38px",
                                minWidth: "38px",
                                height: "24px",
                                borderRadius: "4px",
                            }}
                        >
                            Hủy
                        </Button>
                        <Button
                            onClick={handleDeleteFolder}
                            variant="contained"
                            color="error"
                            sx={{
                                width: "38px",
                                minWidth: "38px",
                                height: "24px",
                                borderRadius: "4px",
                            }}
                        >
                            Xóa
                        </Button>
                    </div>
                </div>
            </Popover>
        </div>
    );
};

export default FolderDeleteButton;
