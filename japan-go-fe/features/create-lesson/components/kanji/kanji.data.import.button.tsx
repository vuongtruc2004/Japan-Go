"use client";
import React, { useState, useTransition } from "react";
import { Button, Divider, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import CloseIcon from "@mui/icons-material/Close";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";
import { importKanjiDataActions } from "@/features/create-lesson/actions/create.lesson.actions";
import { IImportKanjiDataState } from "@/features/create-lesson/types/create.lesson.state.type";
import { useKanjiData } from "@/features/create-lesson/contexts/kanji.data.provider";

const KanjiDataImportButton = () => {
    const t = useTranslations();
    const { setKanjiPages } = useKanjiData();
    const [open, setOpen] = useState(false);

    const [formState, setFormState] = useState<IImportKanjiDataState | null>(
        null,
    );
    const [isPending, startTransition] = useTransition();

    const handleClose = () => {
        setFormState(null);
        setOpen(false);
    };

    const formAction = (formData: FormData) => {
        startTransition(async () => {
            const result = await importKanjiDataActions(formData);
            startTransition(() => {
                if (result && result.success) {
                    setKanjiPages(result.kanjiPages);
                    handleClose();
                } else {
                    setFormState(result);
                }
            });
        });
    };

    return (
        <>
            <Button
                variant="outlined"
                color="primary"
                onClick={() => setOpen(true)}
            >
                <AddOutlinedIcon />
                {t("Common.importData")}
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 w-200 -translate-x-1/2 -translate-y-1/2 rounded-md">
                    <h1 className="px-5 pt-5">
                        <span className="mr-1 font-semibold">
                            {t("Common.importData")}.
                        </span>
                        {t("Common.importDataFrom")}
                    </h1>
                    <form action={formAction} key="kanji-data-import-form">
                        <TextFieldCustom
                            name="kanji-data"
                            placeholder={t(
                                "Pages.yourLibrary.lesson.importKanjiDataPlaceholder",
                            )}
                            fullWidth
                            multiline
                            rows={10}
                            defaultValue={
                                formState ? formState.kanjiData.value : ""
                            }
                            sx={{
                                padding: "20px",
                            }}
                        />

                        <Divider />

                        <div className="flex items-center justify-end gap-x-3 p-5">
                            <Button
                                onClick={handleClose}
                                sx={{ columnGap: "8px" }}
                                variant="outlined"
                                color="error"
                                disabled={isPending}
                            >
                                <CloseIcon fontSize="small" />
                                {t("Common.cancel")}
                            </Button>

                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                loading={isPending}
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

export default KanjiDataImportButton;
