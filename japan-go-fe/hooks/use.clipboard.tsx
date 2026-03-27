"use client";
import { useCallback, useState } from "react";

interface UseClipboardProps {
    resetAfter?: number;
}

export const useClipboard = (props?: UseClipboardProps) => {
    const { resetAfter = 1000 } = props || {};
    const [isCopied, setIsCopied] = useState(false);

    const copy = useCallback(
        async (text?: string) => {
            if (!text) return false;

            try {
                await navigator.clipboard.writeText(text);
                setIsCopied(true);

                setTimeout(() => setIsCopied(false), resetAfter);
                return true;
            } catch (err) {
                console.error("Copy failed:", err);
                return false;
            }
        },
        [resetAfter],
    );

    return {
        isCopied,
        copy,
    };
};
