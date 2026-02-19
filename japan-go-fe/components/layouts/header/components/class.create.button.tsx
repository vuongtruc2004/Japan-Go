import CloseIcon from "@mui/icons-material/Close";
import SchoolOutlinedIcon from "@mui/icons-material/SchoolOutlined";
import { Button, Modal, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useState } from "react";

const ClassCreateButton = () => {
    const t = useTranslations("Common");
    const [open, setOpen] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };
    return (
        <div>
            <button
                className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150"
                onClick={() => setOpen(true)}
            >
                <SchoolOutlinedIcon />
                <p className="text-sm font-semibold whitespace-nowrap">
                    {t("class.title")}
                </p>
            </button>

            <Modal open={open}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 w-150 -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="mb-3 font-semibold">{t("class.create")}</h1>

                    <form action={""}>
                        <TextField
                            name="class-name"
                            placeholder={t("class.placeholder")}
                            size="small"
                            fullWidth
                            slotProps={{
                                input: {
                                    style: {
                                        height: "40px",
                                    },
                                },
                            }}
                            // error={state != null && state.className.isError}
                            // helperText={
                            //     <span className="text-tc-error">
                            //         {state && state.className.errorMessage}
                            //     </span>
                            // }
                        />

                        <div className="mt-3 flex items-center justify-end gap-x-3">
                            <Button
                                // loading={pending}
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
        </div>
    );
};

export default ClassCreateButton;
