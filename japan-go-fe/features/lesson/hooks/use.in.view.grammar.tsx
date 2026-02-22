"use client";
import { useEffect, useState } from "react";

export const useInViewGrammar = (grammarIds: string[], offset = 200) => {
    const [activeId, setActiveId] = useState<string | null>(null);

    useEffect(() => {
        if (!grammarIds.length) return;

        const observer = new IntersectionObserver(
            (entries) => {
                entries.forEach((entry) => {
                    if (entry.isIntersecting) {
                        setActiveId(entry.target.id);
                    }
                });
            },
            {
                root: null,
                rootMargin: `-${offset}px 0px -70% 0px`,
                threshold: 0,
            },
        );

        grammarIds.forEach((id) => {
            const el = document.getElementById(id);
            if (el) observer.observe(el);
        });

        return () => observer.disconnect();
    }, [grammarIds, offset]);

    return activeId;
};
