import React, { useState } from "react";
import { Modal } from "@mui/material";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import { useTranslations } from "next-intl";
import ContentCopyOutlinedIcon from "@mui/icons-material/ContentCopyOutlined";
import Link from "next/link";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { DecoratorClassName } from "@/types/enums/share.enum";
import { useClipboard } from "@/hooks/use.clipboard";
import { toast } from "react-toastify";

const JapaneseView = ({ japanese }: { japanese: string }) => {
    const t = useTranslations();
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const { copy } = useClipboard();

    const handleCopy = async () => {
        const isCopied = await copy(japanese);
        if (isCopied) {
            toast.success(t("Common.copied"), {
                hideProgressBar: true,
                closeOnClick: true,
                autoClose: 2000,
            });
            setOpen(false);
        }
    };

    return (
        <>
            <button
                className={`${DecoratorClassName.PRIMARY} font-noto-sans-jp cursor-pointer rounded-md border px-4 py-2 text-4xl text-nowrap transition-all duration-150`}
                onClick={handleOpen}
            >
                {japanese}
            </button>

            <Modal open={open} onClose={handleClose}>
                <div className="bg-bgc-app border-bdc-primary absolute top-1/2 left-1/2 flex -translate-x-1/2 -translate-y-1/2 flex-col items-center rounded-md border px-8 py-10">
                    <h1 className="text-8xl select-none">{japanese}</h1>

                    <div className="mt-8 flex items-center gap-x-3">
                        <Link
                            className={`${DecoratorClassName.PRIMARY} flex w-28 items-center gap-x-1.5 rounded-md border px-4 py-2 transition-all duration-150`}
                            href={`https://mazii.net/vi-VN/search/word/javi/${japanese}`}
                            target="_blank"
                        >
                            <SearchOutlinedIcon fontSize="small" />
                            {t("Pages.lesson.kanji.searchByMazii")}
                        </Link>

                        <Link
                            className={`${DecoratorClassName.PRIMARY} flex w-28 items-center gap-x-1.5 rounded-md border px-4 py-2 transition-all duration-150`}
                            href={`https://www.google.com/search?q=${encodeURIComponent(japanese)}+意味`}
                            target="_blank"
                        >
                            <SearchOutlinedIcon fontSize="small" />
                            {t("Pages.lesson.kanji.searchByWeblio")}
                        </Link>
                    </div>

                    <IconButtonCustom
                        sx={{
                            position: "absolute",
                            top: "8px",
                            right: "6px",
                        }}
                        hasBgColor={false}
                        onClick={handleCopy}
                    >
                        <ContentCopyOutlinedIcon fontSize="small" />
                    </IconButtonCustom>
                </div>
            </Modal>
        </>
    );
};

export default JapaneseView;
