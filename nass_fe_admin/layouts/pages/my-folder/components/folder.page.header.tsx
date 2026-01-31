import SearchIcon from "@mui/icons-material/Search";
import { InputAdornment, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import FolderCreateButton from "../features/folder.create.button";

const FolderPageHeader = () => {
    const t = useTranslations("Pages.folder");
    return (
        <div className="mb-3">
            <h1 className="text-tc-primary mb-3 font-semibold">
                {t("myFoldersTitle")}
            </h1>
            <form action="" className="flex items-center justify-between">
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
                <FolderCreateButton />
            </form>
        </div>
    );
};

export default FolderPageHeader;
