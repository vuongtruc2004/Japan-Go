"use client";
import { useRouter } from "@/i18n/navigation";
import CloseIcon from "@mui/icons-material/Close";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import { Button, Modal, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useActionState, useEffect, useState } from "react";
import { folderCreate } from "./folder.create.action";

const initialState: IFolderCreateState | null = null;

const FolderCreateButton = () => {
    const t = useTranslations("Common");
    const [open, setOpen] = useState(false);
    const { refresh } = useRouter();
    const [state, formAction, pending] = useActionState(
        folderCreate,
        initialState,
    );

    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
        const fetching = () => {
            if (state?.response) {
                handleClose();
                refresh();
            }
        };
        fetching();
    }, [state, refresh]);

    return (
        <>
            <button
                className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150"
                onClick={() => setOpen(true)}
            >
                <FolderOutlinedIcon />
                <p className="text-sm font-semibold whitespace-nowrap">
                    {t("folder.title")}
                </p>
            </button>

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-150 -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="mb-3 font-semibold">{t("folder.create")}</h1>

                    <form action={formAction}>
                        <TextField
                            name="folder-name"
                            placeholder={t("folder.placeholder")}
                            size="small"
                            fullWidth
                            slotProps={{
                                input: {
                                    style: {
                                        height: "36px",
                                    },
                                },
                            }}
                            error={state != null && state.folderName.isError}
                            helperText={
                                <span className="text-tc-error">
                                    {state && state.folderName.errorMessage}
                                </span>
                            }
                        />

                        <div className="mt-3 flex items-center justify-end gap-x-3">
                            <Button
                                loading={pending}
                                onClick={handleClose}
                                sx={{ columnGap: "8px" }}
                                variant="outlined"
                                color="error"
                            >
                                <CloseIcon fontSize="small" />
                                {t("cancel")}
                            </Button>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                            >
                                {t("create")}
                            </Button>
                        </div>
                    </form>
                </div>
            </Modal>
        </>
    );
};

export default FolderCreateButton;

