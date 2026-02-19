import React from "react";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import FolderCreateButton from "@/components/domain/folder/folder.create.button";
import { Button, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import WrapBox from "@/components/ui/wrap.box";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

const FolderSearchBox = () => {
    const t = useTranslations();
    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">{t("Common.search")}</h1>

            <div className="flex items-center justify-between gap-x-3">
                <form action="" className="flex-1">
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
                            "Pages.yourLibrary.folder.searchPlaceholder",
                        )}
                        fullWidth
                    />
                </form>

                <FolderCreateButton
                    buttonElement={
                        <Button variant="contained" color="primary">
                            <AddOutlinedIcon fontSize="small" />
                            {t("Pages.yourLibrary.folder.createFolder")}
                        </Button>
                    }
                />
            </div>
        </WrapBox>
    );
};

export default FolderSearchBox;
