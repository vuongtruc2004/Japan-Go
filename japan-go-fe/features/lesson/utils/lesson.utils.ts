"use client";
import { DecoratorClassName } from "@/types/enums/share.enum";

export const getKanjiPageMoveButtonClassname = (
    activeIndex: number,
    index: number,
): string => {
    if (activeIndex === index) return DecoratorClassName.GREEN;
    return DecoratorClassName.PRIMARY;
};

export const scrollToElementWithOffset = (elementId: string, offset = 200) => {
    if (typeof window === "undefined") return;

    const el = document.getElementById(elementId);
    if (!el) return;

    const y = el.getBoundingClientRect().top + window.scrollY - offset;

    const maxScroll =
        document.documentElement.scrollHeight - window.innerHeight;

    window.scrollTo({
        top: Math.min(y, maxScroll),
        behavior: "smooth",
    });
};
