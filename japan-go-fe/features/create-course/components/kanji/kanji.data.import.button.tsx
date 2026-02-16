"use client";
import React, { useState, useTransition } from "react";
import { Button, Divider, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import CloseIcon from "@mui/icons-material/Close";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import { importKanjiDataActions } from "@/features/create-course/actions/create.course.actions";
import { IImportKanjiDataState } from "@/features/create-course/types/create.course.state.type";

const KanjiDataImportButton = ({
    setKanjiPages,
}: {
    setKanjiPages: React.Dispatch<React.SetStateAction<KanjiPageRequest[]>>;
}) => {
    const t = useTranslations();
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
            const formState = await importKanjiDataActions(formData);
            if (formState && formState.success) {
                startTransition(() => {
                    setKanjiPages(formState.kanjiPages);
                    setOpen(false);
                });
            }
        });
    };

    return (
        <div>
            <Button
                variant="outlined"
                color="primary"
                onClick={() => setOpen(true)}
            >
                <AddOutlinedIcon />
                {t("Common.importData")}
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-page f absolute top-1/2 left-1/2 w-200 -translate-x-1/2 -translate-y-1/2 rounded-md">
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
                                "Pages.yourLibrary.course.importKanjiDataPlaceholder",
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
        </div>
    );
};

export default KanjiDataImportButton;
