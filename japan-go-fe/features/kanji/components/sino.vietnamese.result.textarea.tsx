import { Box, TextField } from "@mui/material";
import { useTranslations } from "next-intl";
import { useClipboard } from "@/hooks/use.clipboard";

export default function SinoVietnameseResultTextArea({
    sinoVietnamese,
}: Readonly<{
    sinoVietnamese: string;
}>) {
    const t = useTranslations("Pages.kanji.sinoVietnameseImport");
    const { isCopied, copy } = useClipboard();
    return (
        <div className="relative">
            <TextField
                disabled={sinoVietnamese === ""}
                defaultValue={sinoVietnamese}
                fullWidth
                multiline
                rows={12}
                slotProps={{
                    input: {
                        readOnly: true,
                        onMouseDown: (e) => {
                            e.preventDefault();
                            copy(sinoVietnamese);
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
                    visibility: isCopied ? "visible" : "hidden",
                    transition: "all .5s",
                    color: "var(--color-tc-highlight)",
                }}
            >
                {t("copied")}
            </Box>
        </div>
    );
}
