import React, { useState } from "react";
import { KanjiResponse } from "@/types/api/responses/kanji.response";
import { Button, Divider, Modal, TextField } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import CloseIcon from "@mui/icons-material/Close";
import { useTranslations } from "next-intl";
import { useRouter } from "next/navigation";
import { toast } from "react-toastify";
import { createSinoVietnamese } from "@/services/kanji.service";

const CreateSinoVietnameseButton = ({ kanji }: { kanji: KanjiResponse }) => {
    const t = useTranslations();
    const { refresh } = useRouter();
    const [open, setOpen] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };

    const handleSubmit = async (e: React.SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();

        const formData = new FormData(e.currentTarget);
        const value = formData.get("readingText");
        const readingText = typeof value === "string" ? value.trim() : "";

        if (readingText !== "") {
            await createSinoVietnamese(kanji.id, readingText);
            toast.success(t("Pages.kanjiDetails.createSinoVietnameseSuccess"), {
                hideProgressBar: true,
                closeOnClick: true,
                autoClose: 2000,
            });
            refresh();
        }
        handleClose();
    };

    return (
        <>
            <Button
                variant="outlined"
                color="success"
                size="small"
                startIcon={<AddIcon fontSize="small" />}
                onClick={() => setOpen(true)}
            >
                {t("Pages.kanjiDetails.createSinoVietnamese")}
            </Button>

            <Modal open={open} onClose={handleClose}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 w-120 -translate-x-1/2 -translate-y-1/2 rounded-xl p-6">
                    <form onSubmit={handleSubmit}>
                        <h1 className="mb-3 text-lg font-semibold">
                            {t("Pages.kanjiDetails.createSinoVietnamese")}
                        </h1>
                        <Divider className="mb-4" />

                        <TextField
                            fullWidth
                            name="readingText"
                            size="small"
                            placeholder={t(
                                "Pages.kanjiDetails.createSinoVietnamesePlaceholder",
                            )}
                        />
                        <div className="mt-4 flex items-center justify-end gap-x-3">
                            <Button
                                onClick={handleClose}
                                startIcon={<CloseIcon fontSize="small" />}
                                variant="outlined"
                                color="error"
                            >
                                {t("Common.cancel")}
                            </Button>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                            >
                                {t("Common.createNew")}
                            </Button>
                        </div>
                    </form>
                </div>
            </Modal>
        </>
    );
};

export default CreateSinoVietnameseButton;
