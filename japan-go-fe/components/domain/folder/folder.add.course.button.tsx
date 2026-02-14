"use client";
import { Button, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import CloseOutlinedIcon from "@mui/icons-material/CloseOutlined";
import {
    ButtonHTMLAttributes,
    cloneElement,
    ReactElement,
    useState,
} from "react";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

const FolderAddCourseButton = ({
    buttonElement,
}: {
    buttonElement: ReactElement<ButtonHTMLAttributes<HTMLButtonElement>>;
}) => {
    const t = useTranslations();
    const [open, setOpen] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <>
            {cloneElement(buttonElement, { onClick: () => setOpen(true) })}

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-150 -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="mb-3 font-semibold">
                        {t("Pages.yourLibrary.folder.addCourse")}
                    </h1>

                    <div className="flex items-center justify-between">
                        <p className="text-sm font-semibold">
                            {t("Common.course.title")}
                        </p>
                        <div className="text-tc-highlight hover:text-tc-highlight-darker flex cursor-pointer items-center gap-x-0.5 text-sm font-semibold transition-all duration-150">
                            <AddOutlinedIcon fontSize="small" />
                            {t("Common.course.create")}
                        </div>
                    </div>

                    <TooltipCustom
                        title={t("Common.closeWindow")}
                        color={"--color-tc-error"}
                        placement="right"
                    >
                        <span className="hover:bg-hbgc-app hover:text-tc-error absolute top-3 right-3 flex h-9 w-9 cursor-pointer items-center justify-center rounded-full transition-all duration-150">
                            <CloseOutlinedIcon fontSize="small" />
                        </span>
                    </TooltipCustom>

                    <div className="flex justify-end">
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleClose}
                        >
                            {t("Common.complete")}
                        </Button>
                    </div>
                </div>
            </Modal>
        </>
    );
};

export default FolderAddCourseButton;
