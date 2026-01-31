import { Box, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useState } from "react";

export default function SinoVnResultTextArea({ sinoVn }: { sinoVn: string }) {
    const t = useTranslations("Pages.kanji.sinoVietnameseImport");
    const [copied, setCopied] = useState(false);
    const handleCopy = async () => {
        if (copied || sinoVn === "") return;
        await navigator.clipboard.writeText(sinoVn ?? "");
        setCopied(true);
        setTimeout(() => setCopied(false), 1000);
    };

    return (
        <div className="relative">
            <TextField
                disabled={sinoVn === ""}
                defaultValue={sinoVn}
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
                        cursor: sinoVn === "" ? "no-drop" : "pointer",
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
