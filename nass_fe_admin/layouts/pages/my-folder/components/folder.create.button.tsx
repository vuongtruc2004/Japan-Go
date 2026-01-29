"use client";
import AddIcon from "@mui/icons-material/Add";
import CloseIcon from "@mui/icons-material/Close";
import { Button, Modal, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useState } from "react";

const FolderCreateButton = () => {
    const t = useTranslations("Pages.folder");
    const [open, setOpen] = useState(false);

    return (
        <>
            <Button
                onClick={() => setOpen(true)}
                variant="outlined"
                color="success"
                sx={{ width: "max-content" }}
            >
                <AddIcon fontSize="small" />
                <p className="ml-1.5 text-sm">{t("create")}</p>
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-[600px] -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="text-tc-primary font-semibold">
                        Chép và dán dữ liệu vào đây để lấy phiên âm Hán Việt của
                        các từ đó
                    </h1>

                    <form action="" className="my-3">
                        <TextField
                            name="folder-name"
                            placeholder="Nhập tên thư mục"
                            size="small"
                            fullWidth
                        />
                    </form>

                    <div className="mt-5 flex items-center justify-end gap-x-3">
                        <Button
                            onClick={() => setOpen(false)}
                            sx={{ columnGap: "8px" }}
                            variant="contained"
                            color="secondary"
                        >
                            <CloseIcon fontSize="small" />
                            Hủy
                        </Button>
                        <Button
                            type="submit"
                            variant="contained"
                            color="primary"
                        >
                            Tạo
                        </Button>
                    </div>
                </div>
            </Modal>
        </>
    );
};

export default FolderCreateButton;
