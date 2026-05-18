"use client";
import React, { useState } from "react";
import { Button, Modal, TextField } from "@mui/material";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import SinoVietnameseResultTextArea from "@/features/kanji/components/sino.vietnamese.result.textarea";
import CloseIcon from "@mui/icons-material/Close";
import { useTranslations } from "next-intl";
import { formatQuizletData } from "@/services/deck.service";

const FormatQuizletButton = () => {
    const t = useTranslations();

    const [open, setOpen] = useState(false);
    const [result, setResult] = useState("");

    const handleSubmit = async (e: React.SubmitEvent) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const value = formData.get("raw");
        const raw = typeof value === "string" ? value.trim() : "";

        const result = await formatQuizletData(raw);
        setResult(result);
    };

    const handleClose = () => {
        setOpen(false);
        setResult("");
    };

    return (
        <>
            <Button
                variant="outlined"
                color="success"
                onClick={() => setOpen(true)}
            >
                <FileUploadOutlinedIcon fontSize="small" />
                <p className="ml-1.5 text-sm">
                    {t("Pages.flashcard.buttonTitle")}
                </p>
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 w-250 -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="font-semibold">
                        {t("Pages.flashcard.title")}
                    </h1>

                    <form onSubmit={handleSubmit} className="my-3">
                        <div className="grid w-full grid-cols-[2fr_1fr] items-start gap-x-5">
                            <TextField
                                name="raw"
                                multiline
                                rows={12}
                                placeholder={t(
                                    "Pages.kanji.sinoVietnameseImport.inputPlaceholder",
                                )}
                            />

                            <SinoVietnameseResultTextArea
                                sinoVietnamese={result}
                            />
                        </div>

                        <div className="mt-5 flex items-center justify-end gap-x-3">
                            <Button
                                onClick={handleClose}
                                sx={{ columnGap: "8px" }}
                                variant="outlined"
                                color="error"
                            >
                                <CloseIcon fontSize="small" />
                                {t("Common.cancel")}
                            </Button>

                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                            >
                                {t("Common.confirm")}
                            </Button>
                        </div>
                    </form>
                </div>
            </Modal>
        </>
    );
};

export default FormatQuizletButton;
