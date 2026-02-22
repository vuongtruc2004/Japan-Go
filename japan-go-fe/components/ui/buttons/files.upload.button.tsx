import React from "react";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import { isMarkdownFile } from "@/utils/file.validator";

const FilesUploadButton = ({
    setFiles,
    errorMessage,
    setErrorMessage,
}: {
    setFiles: React.Dispatch<React.SetStateAction<File[]>>;
    errorMessage: string;
    setErrorMessage: React.Dispatch<React.SetStateAction<string>>;
}) => {
    const t = useTranslations();

    const handleFilesChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const uploadedFiles = Array.from(event.target.files || []);

        const isValid = uploadedFiles.every(isMarkdownFile);

        if (!isValid) {
            setErrorMessage(t("Validator.notMarkdownFile"));
            return;
        }

        setFiles(uploadedFiles);
        setErrorMessage("");
    };

    return (
        <label htmlFor="files" className="flex w-max">
            <input
                name="files"
                id="files"
                accept=".md,text/markdown"
                type="file"
                multiple
                style={{ display: "none" }}
                onChange={handleFilesChange}
            />

            <Button
                variant="outlined"
                color={errorMessage === "" ? "primary" : "error"}
                component={"span"}
            >
                <FileUploadOutlinedIcon fontSize="small" />
                <p className="ml-1.5">{t("Common.uploadFile")}</p>
            </Button>
        </label>
    );
};

export default FilesUploadButton;
