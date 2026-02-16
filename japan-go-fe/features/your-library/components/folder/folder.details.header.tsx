"use client";
import React from "react";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import FolderMoreButton from "@/features/your-library/components/folder/folder.more.button";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import { useTranslations } from "next-intl";
import { Button, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import { AddOutlined } from "@mui/icons-material";
import FolderAddLessonButton from "@/components/domain/folder/folder.add.course.button";
import WrapBox from "@/components/ui/wrap.box";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";

const FolderDetailsHeader = () => {
    const t = useTranslations();
    const { folder } = useFolderDetails();

    return (
        <WrapBox>
            <div className="flex items-center justify-between">
                <div className="flex items-center gap-x-3">
                    <span className="bg-bgc-page flex h-12 w-12 items-center justify-center rounded-md">
                        <FolderOutlinedIcon />
                    </span>
                    <h1 className="font-semibold">{folder.folderName}</h1>
                </div>
                <FolderMoreButton />
            </div>
            <div className="mt-3 flex items-center justify-between gap-x-3">
                <TextFieldCustom
                    slotProps={{
                        input: {
                            startAdornment: (
                                <InputAdornment position="start">
                                    <SearchIcon color="success" />
                                </InputAdornment>
                            ),
                        },
                    }}
                    size="small"
                    variant="outlined"
                    placeholder={t(
                        "Pages.yourLibrary.folder.searchLessonInThisFolderPlaceholder",
                    )}
                    fullWidth
                />
                <FolderAddLessonButton
                    buttonElement={
                        <Button
                            variant="contained"
                            color="primary"
                            sx={{ flexShrink: 0 }}
                        >
                            <AddOutlined fontSize="small" />
                            {t("Pages.yourLibrary.folder.addLesson")}
                        </Button>
                    }
                />
            </div>
        </WrapBox>
    );
};

export default FolderDetailsHeader;
