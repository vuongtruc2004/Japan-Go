import React, { useCallback, useEffect } from "react";
import { STROKE_COLORS } from "@/features/lesson/constants/lesson.constants";

const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));

function getSvgElements(container: HTMLDivElement) {
    const svg = container.querySelector("svg");
    if (!svg) return null;

    return {
        svg,
        paths: Array.from(svg.querySelectorAll("path")),
        numbers: Array.from(svg.querySelectorAll("text")),
    };
}

function resetSvg(
    svg: SVGSVGElement,
    paths: SVGPathElement[],
    numbers: SVGTextElement[],
) {
    paths.forEach((path) => {
        const length = path.getTotalLength();
        path.style.strokeWidth = "3";
        path.style.fill = "none";
        path.style.strokeDasharray = `${length}`;
        path.style.strokeDashoffset = `${length}`;
        path.style.transition = "none";
    });

    numbers.forEach((text) => {
        text.style.opacity = "0";
    });

    svg.getBoundingClientRect(); // force reflow
}

function animateStroke(
    path: SVGPathElement,
    text: SVGTextElement | undefined,
    color: string,
    duration: number,
) {
    path.style.stroke = color;
    path.style.transition = `stroke-dashoffset ${duration}ms linear`;

    if (text) {
        text.style.opacity = "1";
        text.style.fill = color;
    }

    requestAnimationFrame(() => {
        path.style.strokeDashoffset = "0";
    });
}

export function useKanjiVgAnimation({
    containerRef,
    kanjiVg,
    durationPerStroke,
    durationBetweenEachStroke,
}: {
    containerRef: React.RefObject<HTMLDivElement | null>;
    kanjiVg: string;
    durationPerStroke: number;
    durationBetweenEachStroke: number;
}) {
    const run = useCallback(async () => {
        if (!containerRef.current) return;

        const elements = getSvgElements(containerRef.current);
        if (!elements) return;

        const { svg, paths, numbers } = elements;

        resetSvg(svg, paths, numbers);

        for (let i = 0; i < paths.length; i++) {
            animateStroke(
                paths[i],
                numbers[i],
                STROKE_COLORS[i % STROKE_COLORS.length],
                durationPerStroke,
            );

            await sleep(durationPerStroke + durationBetweenEachStroke);
        }
    }, [containerRef, durationPerStroke, durationBetweenEachStroke]);

    useEffect(() => {
        run();
    }, [kanjiVg, run]);

    return { run };
}
