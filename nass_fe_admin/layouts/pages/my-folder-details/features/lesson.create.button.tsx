"use client";
import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";
import AddIcon from "@mui/icons-material/Add";
import CloseIcon from "@mui/icons-material/Close";
import { Button, Modal } from "@mui/material";
import LessonSelectTabs from "./lesson.select.tabs";

const LessonCreateButton = () => {
    const { openCreateModal, setOpenCreateModal } = useFolderDetails();

    const handleClose = () => {
        setOpenCreateModal(false);
    };

    return (
        <>
            <Button
                onClick={() => setOpenCreateModal(true)}
                variant="outlined"
                color="success"
                sx={{ width: "max-content" }}
            >
                <AddIcon />
                <p>Thêm bài giảng</p>
            </Button>

            <Modal open={openCreateModal}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-[600px] -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="text-tc-primary mb-3 font-semibold">
                        Thêm bài giảng
                    </h1>

                    <LessonSelectTabs />

                    <div className="mt-3 flex items-center justify-end gap-x-3">
                        <Button
                            onClick={handleClose}
                            type="submit"
                            variant="contained"
                            color="primary"
                        >
                            Hoàn tất
                        </Button>
                    </div>

                    <TooltipCustom title="Đóng cửa sổ" arrow>
                        <Button
                            onClick={handleClose}
                            sx={{
                                columnGap: "8px",
                                position: "absolute",
                                top: "12px",
                                right: "20px",
                                width: "36px",
                                minWidth: "36px",
                                borderRadius: "50%",
                            }}
                            variant="text"
                            color="secondary"
                        >
                            <CloseIcon fontSize="small" />
                        </Button>
                    </TooltipCustom>
                </div>
            </Modal>
        </>
    );
};

export default LessonCreateButton;
