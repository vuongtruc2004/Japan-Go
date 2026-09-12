"use client";
import React, { useState } from "react";
import { Button, CircularProgress, Modal, TextField } from "@mui/material";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import SinoVietnameseResultTextArea from "@/features/kanji/components/sino.vietnamese.result.textarea";
import CloseIcon from "@mui/icons-material/Close";
import TranslateIcon from "@mui/icons-material/Translate";
import SpellcheckIcon from "@mui/icons-material/Spellcheck";
import MenuBookIcon from "@mui/icons-material/MenuBook";
import { useTranslations } from "next-intl";
import {
    formatQuizletDataKanji,
    formatQuizletDataHiragana,
    formatQuizletDataSentence,
} from "@/services/deck.service";

type FormatType = "kanji" | "hiragana" | "sentence";

const FormatQuizletButton = () => {
    const t = useTranslations();

    const [open, setOpen] = useState(false);
    const [formatType, setFormatType] = useState<FormatType>("kanji");
    const [result, setResult] = useState("");
    const [loading, setLoading] = useState(false);

    const handleOpenModal = (type: FormatType) => {
        setFormatType(type);
        setResult("");
        setOpen(true);
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const value = formData.get("raw");
        const raw = typeof value === "string" ? value.trimEnd() : "";

        if (!raw) return;

        setLoading(true);
        try {
            let res = "";
            if (formatType === "kanji") {
                res = await formatQuizletDataKanji(raw);
            } else if (formatType === "hiragana") {
                res = await formatQuizletDataHiragana(raw);
            } else if (formatType === "sentence") {
                res = await formatQuizletDataSentence(raw);
            }
            setResult(res);
        } catch (err) {
            console.error("Format Quizlet Error:", err);
        } finally {
            setLoading(false);
        }
    };

    const handleClose = () => {
        setOpen(false);
        setResult("");
    };

    const getModalTitle = () => {
        switch (formatType) {
            case "kanji":
                return t("Pages.flashcard.titleKanji");
            case "hiragana":
                return t("Pages.flashcard.titleHiragana");
            case "sentence":
                return t("Pages.flashcard.titleSentence");
            default:
                return t("Pages.flashcard.title");
        }
    };

    return (
        <>
            <div className="flex flex-wrap items-center gap-3">
                <Button
                    variant="outlined"
                    color="success"
                    onClick={() => handleOpenModal("kanji")}
                >
                    <TranslateIcon fontSize="small" />
                    <p className="ml-1.5 text-sm font-medium">
                        {t("Pages.flashcard.buttonTitleKanji")}
                    </p>
                </Button>

                <Button
                    variant="outlined"
                    color="info"
                    onClick={() => handleOpenModal("hiragana")}
                >
                    <SpellcheckIcon fontSize="small" />
                    <p className="ml-1.5 text-sm font-medium">
                        {t("Pages.flashcard.buttonTitleHiragana")}
                    </p>
                </Button>

                <Button
                    variant="outlined"
                    color="warning"
                    onClick={() => handleOpenModal("sentence")}
                >
                    <MenuBookIcon fontSize="small" />
                    <p className="ml-1.5 text-sm font-medium">
                        {t("Pages.flashcard.buttonTitleSentence")}
                    </p>
                </Button>
            </div>

            <Modal open={open} onClose={handleClose}>
                <div className="bg-bgc-app border-bdc-primary absolute top-1/2 left-1/2 w-250 -translate-x-1/2 -translate-y-1/2 rounded-lg border p-6 shadow-2xl">
                    <h1 className="font-semibold text-lg">
                        {getModalTitle()}
                    </h1>

                    <form onSubmit={handleSubmit} className="my-4">
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
                                disabled={loading}
                                startIcon={
                                    loading ? (
                                        <CircularProgress
                                            size={16}
                                            color="inherit"
                                        />
                                    ) : null
                                }
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
