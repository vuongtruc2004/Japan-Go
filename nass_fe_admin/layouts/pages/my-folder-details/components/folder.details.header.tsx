"use client";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import SearchIcon from "@mui/icons-material/Search";

import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";
import { InputAdornment, TextField } from "@mui/material";
import LessonCreateButton from "../features/lesson.create.button";

const FolderDetailsHeader = () => {
    const { folder } = useFolderDetails();
    return (
        <div className="mb-3">
            <div className="mb-3 flex items-center justify-between">
                <div className="flex items-center gap-x-3">
                    <span className="bg-hbgc-highlight flex h-20 w-20 items-center justify-center rounded-md">
                        <FolderOutlinedIcon fontSize="large" />
                    </span>
                    <p className="text-lg font-semibold">{folder.folderName}</p>
                </div>
            </div>

            <div className="flex items-center justify-between">
                <form action="">
                    <TextField
                        slotProps={{
                            input: {
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <SearchIcon color="success" />
                                    </InputAdornment>
                                ),
                                sx: {
                                    width: "400px",
                                    height: "36px",
                                    borderRadius: "6px",
                                    fieldset: {
                                        border: "1px solid var(--color-bdc-muted)",
                                    },
                                    "input::placeholder": {
                                        fontSize: "15.2px",
                                    },
                                },
                            },
                        }}
                        size="small"
                        variant="outlined"
                        placeholder={"Tìm kiếm thư mục"}
                    />
                </form>
                <LessonCreateButton />
            </div>
        </div>
    );
};

export default FolderDetailsHeader;
