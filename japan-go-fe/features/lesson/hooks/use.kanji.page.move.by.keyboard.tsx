import React, { useEffect } from "react";

const wrapIndex = (index: number, length: number) => {
    if (length <= 0) return 0;
    return (index + length) % length;
};

export function useKanjiPageMoveByKeyboard({
    length,
    setActiveIndex,
    enabled = true,
}: {
    length: number;
    setActiveIndex: React.Dispatch<React.SetStateAction<number>>;
    enabled?: boolean;
}) {
    useEffect(() => {
        if (!enabled || length <= 0) return;

        const onKeyDown = (e: KeyboardEvent) => {
            // Disable shortcuts when the user is typing in a text field
            const activeEl = document.activeElement;
            if (
                activeEl &&
                (activeEl.tagName === "INPUT" ||
                    activeEl.tagName === "TEXTAREA" ||
                    activeEl.hasAttribute("contenteditable"))
            ) {
                return;
            }

            if (e.key === "ArrowUp") {
                e.preventDefault();
                setActiveIndex((prev) => wrapIndex(prev - 1, length));
                return;
            }

            if (e.key === "ArrowDown") {
                e.preventDefault();
                setActiveIndex((prev) => wrapIndex(prev + 1, length));
                return;
            }

            // Support jump to page using number keys (1-9, and 0 for page 10)
            if (/^[0-9]$/.test(e.key)) {
                const num = parseInt(e.key, 10);
                const targetIndex = num === 0 ? 9 : num - 1;
                if (targetIndex >= 0 && targetIndex < length) {
                    e.preventDefault();
                    setActiveIndex(targetIndex);
                }
            }
        };

        window.addEventListener("keydown", onKeyDown);
        return () => window.removeEventListener("keydown", onKeyDown);
    }, [enabled, length, setActiveIndex]);
}
