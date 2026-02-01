"use client";
import { Link } from "@/i18n/navigation";
import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import SearchIcon from "@mui/icons-material/Search";
import { InputAdornment, TextField } from "@mui/material";
import LessonCreateButton from "../features/lesson.create.button";

const FolderDetailsHeader = () => {
    const { folder } = useFolderDetails();
    return (
        <div className="mb-3">
            <Link
                href={"/your-folder"}
                className="hover:text-tc-highlight mb-3 flex w-max items-center justify-start gap-x-3 hover:underline"
            >
                <span className="bg-bgc-page flex h-16 w-16 items-center justify-center rounded-md">
                    <FolderOutlinedIcon fontSize="large" />
                </span>
                <p className="text-lg font-semibold">{folder.folderName}</p>
            </Link>

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
                        placeholder={"Tìm kiếm trong thư mục"}
                    />
                </form>
                <LessonCreateButton />
            </div>
        </div>
    );
};

export default FolderDetailsHeader;
