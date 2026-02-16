"use client";
import React from "react";
import { Button, Divider, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import CloseIcon from "@mui/icons-material/Close";
import { TextFieldCustom } from "@/components/ui/mui-custom/text.field.custom";
import { useImportKanjiData } from "@/hooks/create-course/use.import.kanji.data";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

const KanjiDataImportButton = () => {
    const t = useTranslations();
    const [open, setOpen] = React.useState(false);
    const { state, formAction, pending } = useImportKanjiData();

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
                    <form action={formAction}>
                        <TextFieldCustom
                            name="kanji-data"
                            placeholder={t(
                                "Pages.yourLibrary.course.importKanjiDataPlaceholder",
                            )}
                            fullWidth
                            multiline
                            rows={10}
                            sx={{
                                padding: "20px",
                            }}
                        />

                        <Divider />

                        <div className="flex items-center justify-end gap-x-3 p-5">
                            <Button
                                onClick={() => setOpen(false)}
                                sx={{ columnGap: "8px" }}
                                variant="outlined"
                                color="error"
                                disabled={pending}
                            >
                                <CloseIcon fontSize="small" />
                                {t("Common.cancel")}
                            </Button>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                loading={pending}
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
