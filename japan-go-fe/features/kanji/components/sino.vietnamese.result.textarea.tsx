import { Box, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useState } from "react";

export default function SinoVietnameseResultTextArea({ sinoVietnamese }: { sinoVietnamese: string }) {
    const t = useTranslations("Pages.kanji.sinoVietnameseImport");
    const [copied, setCopied] = useState(false);
    const handleCopy = async () => {
        if (copied || sinoVietnamese === "") return;
        await navigator.clipboard.writeText(sinoVietnamese ?? "");
        setCopied(true);
        setTimeout(() => setCopied(false), 1000);
    };

    return (
        <div className="relative">
            <TextField
                disabled={sinoVietnamese === ""}
                defaultValue={sinoVietnamese}
                fullWidth
                multiline
                rows={20}
                slotProps={{
                    input: {
                        readOnly: true,
                        onMouseDown: (e) => {
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
                }}
            />
            <Box
                sx={{
                    position: "absolute",
                    bottom: "40px",
                    right: "20px",
                    fontSize: "14px",
                    visibility: copied ? "visible" : "hidden",
                    transition: "all .5s",
                    color: "var(--color-tc-highlight)",
                }}
            >
                {t("copied")}
            </Box>
        </div>
    );
}

