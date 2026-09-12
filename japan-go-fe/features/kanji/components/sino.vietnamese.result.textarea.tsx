"use client";
import React from "react";
import { Box, TextField, Tooltip } from "@mui/material";
import ContentCopyOutlinedIcon from "@mui/icons-material/ContentCopyOutlined";
import CheckIcon from "@mui/icons-material/Check";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import { useTranslations } from "next-intl";
import { useClipboard } from "@/hooks/use.clipboard";
import { toast } from "react-toastify";

export default function SinoVietnameseResultTextArea({
    sinoVietnamese,
}: Readonly<{
    sinoVietnamese: string;
}>) {
    const t = useTranslations("Pages.kanji.sinoVietnameseImport");
    const { isCopied, copy } = useClipboard({ resetAfter: 2000 });

    const handleCopy = async () => {
        if (!sinoVietnamese) return;
        const success = await copy(sinoVietnamese);
        if (success) {
            toast.success(t("copied"), {
                hideProgressBar: true,
                autoClose: 2000,
            });
        }
    };

    return (
        <div className="relative group">
            <TextField
                disabled={sinoVietnamese === ""}
                value={sinoVietnamese}
                fullWidth
                multiline
                rows={12}
                slotProps={{
                    input: {
                        readOnly: true,
                        onMouseDown: (e) => {
                            if (!sinoVietnamese) return;
                            e.preventDefault();
                            handleCopy();
                        },
                    },
                }}
                placeholder={t("resultPlaceholder")}
                sx={{
                    textarea: {
                        cursor: sinoVietnamese === "" ? "no-drop" : "pointer",
                        whiteSpace: "pre",
                        overflowX: "auto",
                    },
                    "& .MuiOutlinedInput-root": {
                        transition: "all 0.3s ease",
                        ...(isCopied && {
                            borderColor: "var(--color-tc-highlight, #10b981)",
                            boxShadow: "0 0 0 3px rgba(16, 185, 129, 0.25)",
                        }),
                    },
                }}
            />

            {/* Quick Copy Button */}
            {sinoVietnamese && (
                <div className="absolute top-2.5 right-2.5 z-10">
                    <Tooltip title={isCopied ? t("copied") : "Sao chép"}>
                        <button
                            type="button"
                            onClick={(e) => {
                                e.stopPropagation();
                                handleCopy();
                            }}
                            className={`flex items-center gap-1 rounded-md px-2.5 py-1 text-xs font-medium transition-all duration-200 shadow-sm cursor-pointer ${
                                isCopied
                                    ? "bg-emerald-600 text-white scale-105"
                                    : "bg-bgc-app border border-gray-300 text-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:text-gray-200 dark:hover:bg-gray-800"
                            }`}
                        >
                            {isCopied ? (
                                <>
                                    <CheckIcon sx={{ fontSize: 16 }} />
                                    <span>{t("copied")}</span>
                                </>
                            ) : (
                                <>
                                    <ContentCopyOutlinedIcon sx={{ fontSize: 15 }} />
                                    <span>Sao chép</span>
                                </>
                            )}
                        </button>
                    </Tooltip>
                </div>
            )}

            {/* Floating Copy Confirmation Badge */}
            <Box
                sx={{
                    position: "absolute",
                    bottom: "20px",
                    right: "20px",
                    display: "flex",
                    alignItems: "center",
                    gap: "6px",
                    padding: "6px 14px",
                    borderRadius: "9999px",
                    backgroundColor: "#10b981",
                    color: "#ffffff",
                    fontSize: "13px",
                    fontWeight: 600,
                    boxShadow: "0 4px 14px rgba(16, 185, 129, 0.4)",
                    opacity: isCopied ? 1 : 0,
                    transform: isCopied
                        ? "translateY(0) scale(1)"
                        : "translateY(10px) scale(0.9)",
                    pointerEvents: "none",
                    transition: "all 0.3s cubic-bezier(0.34, 1.56, 0.64, 1)",
                }}
            >
                <CheckCircleOutlineIcon sx={{ fontSize: 18 }} />
                <span>{t("copied")}!</span>
            </Box>
        </div>
    );
}
