"use client";
import { useRouter } from "@/i18n/navigation";
import { removeLessonFromFolder } from "@/libs/api/folder/folder";
import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";
import RemoveIcon from "@mui/icons-material/Remove";
import WarningAmberIcon from "@mui/icons-material/WarningAmber";
import { Button, Popover } from "@mui/material";
import { useState } from "react";

const LessonDeleteButton = ({ lesson }: { lesson: LessonResponse }) => {
    const { folder } = useFolderDetails();
    const { refresh } = useRouter();
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleDeleteFolder = async () => {
        await removeLessonFromFolder({
            folderId: folder.id,
            lessonId: lesson.id,
        });
        refresh();
    };

    return (
        <div>
            <Button
                onClick={handleClick}
                variant="text"
                color="error"
                sx={{ columnGap: "8px" }}
            >
                <RemoveIcon fontSize="small" />
                <p>Xóa khỏi thư mục</p>
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
                            <p className="font-semibold">Xóa khỏi thư mục</p>

                            <p>
                                Bạn có chắc chắn muốn xóa bài giảng khỏi thư mục
                                này không?
                            </p>
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

export default LessonDeleteButton;
