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
            if (e.key === "ArrowUp") {
                e.preventDefault();
                setActiveIndex((prev) => wrapIndex(prev - 1, length));
                return;
            }

            if (e.key === "ArrowDown") {
                e.preventDefault();
                setActiveIndex((prev) => wrapIndex(prev + 1, length));
            }
        };

        window.addEventListener("keydown", onKeyDown);
        return () => window.removeEventListener("keydown", onKeyDown);
    }, [enabled, length, setActiveIndex]);
}
