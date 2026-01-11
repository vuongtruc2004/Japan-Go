import { Box, TextField } from "@mui/material";
import { useState } from "react";

export default function SinoVnResultTextArea({ sinoVn }: { sinoVn: string }) {
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
                placeholder="Kết quả sẽ hiển thị ở đây"
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
                Đã sao chép
            </Box>
        </div>
    );
}
