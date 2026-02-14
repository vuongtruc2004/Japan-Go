import React from "react";
import { useTranslations } from "next-intl";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import FolderCreateButton from "@/components/domain/folder/folder.create.button";
import { Button, Divider, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

const FolderSearchBox = () => {
    const t = useTranslations();
    return (
        <div className="mb-3 flex flex-col gap-y-3">
            <h1 className="font-semibold">{t("Common.search")}</h1>

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
                            {t("Pages.yourLibrary.folder.createFolder")}
                        </Button>
                    }
                />
            </div>

            {/*<FolderCreateButton*/}
            {/*    buttonElement={*/}
            {/*        <Button*/}
            {/*            variant="outlined"*/}
            {/*            color="primary"*/}
            {/*            sx={{ width: "max-content", borderStyle: "dashed" }}*/}
            {/*        >*/}
            {/*            <AddIcon fontSize="small" />*/}
            {/*            {t("Pages.yourLibrary.folder.createFolder")}*/}
            {/*        </Button>*/}
            {/*    }*/}
            {/*/>*/}
            <Divider />
        </div>
    );
};

export default FolderSearchBox;
