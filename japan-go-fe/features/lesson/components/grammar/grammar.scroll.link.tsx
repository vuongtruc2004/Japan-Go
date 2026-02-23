"use client";
import React from "react";
import { scrollToElementWithOffset } from "@/features/lesson/utils/lesson.utils";

const GrammarScrollLink = ({
    targetId,
    text,
    isSemibold = false,
}: {
    targetId: string;
    text: string;
    isSemibold?: boolean;
}) => {
    return (
        <button
            type="button"
            onClick={() => scrollToElementWithOffset(targetId)}
            className={`hover:bg-hbgc-highlight w-full cursor-pointer rounded-md px-2 py-1 text-left transition-all duration-150 ${isSemibold && "font-semibold"}`}
        >
            {text}
        </button>
    );
};

export default GrammarScrollLink;
