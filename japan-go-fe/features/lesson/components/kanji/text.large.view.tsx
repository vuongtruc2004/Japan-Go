import React, { useState } from "react";
import { Modal } from "@mui/material";

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
                className={`border-bdc-primary hover:border-bdc-primary ${isJapanese && "font-noto-sans-jp"} ${isSemibold && "font-semibold"} hover:bg-hbgc-highlight duration-150" cursor-pointer rounded-md border px-4 py-2 text-lg transition-all`}
                onClick={handleOpen}
            >
                {text}
            </p>

            <Modal open={open} onClose={handleClose}>
                <div className="bg-bgc-page border-bdc-primary absolute top-1/2 left-1/2 flex -translate-x-1/2 -translate-y-1/2 items-center justify-center rounded-md border p-6 text-6xl">
                    {text}
                </div>
            </Modal>
        </>
    );
};

export default TextLargeView;
