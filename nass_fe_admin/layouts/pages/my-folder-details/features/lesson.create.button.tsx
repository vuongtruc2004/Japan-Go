"use client";
import { useRouter } from "@/i18n/navigation";
import AddIcon from "@mui/icons-material/Add";
import CloseIcon from "@mui/icons-material/Close";
import { Button, Modal } from "@mui/material";
import { useState } from "react";
import LessonSelectTabs from "./lesson.select.tabs";

const LessonCreateButton = () => {
    const [open, setOpen] = useState(false);
    const { refresh } = useRouter();
    // const [state, formAction, pending] = useActionState(
    //     folderCreate,
    //     initialState,
    // );

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <>
            <Button
                onClick={() => setOpen(true)}
                variant="outlined"
                color="success"
                sx={{ width: "max-content" }}
            >
                <AddIcon />
                <p>Thêm bài giảng</p>
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-[600px] -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="text-tc-primary mb-3 font-semibold">
                        Thêm bài giảng
                    </h1>

                    <LessonSelectTabs />
                    <div className="mt-3 flex items-center justify-end gap-x-3">
                        <Button
                            onClick={handleClose}
                            sx={{ columnGap: "8px" }}
                            variant="outlined"
                            color="error"
                        >
                            <CloseIcon fontSize="small" />
                            Hủy
                        </Button>
                        <Button
                            type="submit"
                            variant="contained"
                            color="primary"
                        >
                            Thêm
                        </Button>
                    </div>
                </div>
            </Modal>
        </>
    );
};

export default LessonCreateButton;
