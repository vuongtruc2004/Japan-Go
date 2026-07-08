"use client";
import React from "react";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";

interface KanjiDataImportButtonProps {
    file: File | null;
    setFile: (file: File | null) => void;
    setErrorMessage: (msg: string) => void;
}

const KanjiDataImportButton = ({
    file,
    setFile,
    setErrorMessage,
}: KanjiDataImportButtonProps) => {
    const t = useTranslations();

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const selectedFile = event.target.files?.[0] || null;

        if (selectedFile) {
            // Validate extension
            if (!selectedFile.name.endsWith(".xlsx")) {
                setErrorMessage(t("Validator.notExcelFile"));
                return;
            }
            setFile(selectedFile);
            setErrorMessage("");
        }
    };

    return (
        <label htmlFor="excel-file" className="flex w-max cursor-pointer">
            <input
                name="excel-file"
                id="excel-file"
                accept=".xlsx"
                type="file"
                style={{ display: "none" }}
                onChange={handleFileChange}
            />

            <Button
                variant="outlined"
                color={file ? "success" : "primary"}
                component="span"
                sx={{ display: "flex", alignItems: "center", gap: "6px" }}
            >
                <FileUploadOutlinedIcon fontSize="small" />
                <span>
                    {file ? file.name : t("Common.uploadFile")}
                </span>
            </Button>
        </label>
    );
};

export default KanjiDataImportButton;
