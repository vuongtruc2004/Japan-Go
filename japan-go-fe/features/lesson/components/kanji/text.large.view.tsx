import React, { useState } from "react";
import { Modal } from "@mui/material";
import { DecoratorClassName } from "@/types/enums/share.enum";

const TextLargeView = ({
    text,
    isJapanese = false,
    isSemibold = false,
}: {
    text: string;
    isJapanese?: boolean;
    isSemibold?: boolean;
}) => {
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <>
            <p
                className={`${DecoratorClassName.PRIMARY} ${isJapanese && "font-noto-sans-jp"} ${isSemibold && "font-semibold"} duration-150" cursor-pointer rounded-md border px-4 py-2 transition-all`}
                onClick={handleOpen}
            >
                {text}
            </p>

            <Modal open={open} onClose={handleClose}>
                <div className="bg-bgc-app border-bdc-primary absolute top-1/2 left-1/2 flex -translate-x-1/2 -translate-y-1/2 items-center justify-center rounded-md border p-6 text-6xl">
                    {text}
                </div>
            </Modal>
        </>
    );
};

export default TextLargeView;
