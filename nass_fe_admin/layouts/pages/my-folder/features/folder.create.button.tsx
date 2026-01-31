"use client";
import { useRouter } from "@/i18n/navigation";
import AddIcon from "@mui/icons-material/Add";
import CloseIcon from "@mui/icons-material/Close";
import { Button, Modal, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useActionState, useEffect, useState } from "react";
import { folderCreate } from "../actions/folder.create.action";

const initialState: IFolderCreateState | null = null;

const FolderCreateButton = () => {
    const t = useTranslations("Pages.folder");
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
        const test = () => {
            if (state?.response?.statusCode === 201) {
                handleClose();
                refresh();
            }
        };
        test();
    }, [state, refresh]);

    return (
        <>
            <Button
                onClick={() => setOpen(true)}
                variant="outlined"
                color="success"
                sx={{ width: "max-content" }}
            >
                <AddIcon fontSize="small" />
                <p className="ml-1.5 text-sm">{t("createFolderTitle")}</p>
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-[600px] -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="text-tc-primary mb-3 font-semibold">
                        {t("createFolderTitle")}
                    </h1>

                    <form action={formAction}>
                        <TextField
                            name="folder-name"
                            placeholder={t("inputPlaceholder")}
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
