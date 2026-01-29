"use client";

import { useCallback, useEffect, useMemo, useRef, useState } from "react";

type Props = {
    svg: string;
    durationPerStrokeMs?: number;
    gapMs?: number;
};

export default function KanjiAnimatedMultiColor({
    svg,
    durationPerStrokeMs = 600,
    gapMs = 120,
}: Props) {
    const rootRef = useRef<HTMLDivElement>(null);
    const [runKey, setRunKey] = useState(0);

    // Bạn có thể thay bảng màu theo ý
    const colors = useMemo(
        () => [
            "#ef4444", // đỏ
            "#f59e0b", // cam
            "#eab308", // vàng
            "#22c55e", // xanh lá
            "#06b6d4", // cyan
            "#3b82f6", // xanh dương
            "#a855f7", // tím
            "#ec4899", // hồng
        ],
        [],
    );

    const runAnimation = useCallback(() => {
        const root = rootRef.current;
        if (!root) return;

        const svgEl = root.querySelector("svg");
        if (!svgEl) return;

        // ✅ CHỈ lấy stroke paths (bỏ stroke number text)
        const group = svgEl.querySelector('g[id^="kvg:StrokePaths_"]');
        const paths = Array.from((group ?? svgEl).querySelectorAll("path"));

        // Huỷ animation cũ
        paths.forEach((p) => p.getAnimations?.().forEach((a) => a.cancel()));

        let cursor = 0;

        paths.forEach((path, i) => {
            // style cơ bản
            path.style.fill = "none";
            path.style.strokeLinecap = "round";
            path.style.strokeLinejoin = "round";
            path.style.strokeWidth = "3";

            // ✅ mỗi nét 1 màu
            path.style.stroke = colors[i % colors.length];

            // dash animate
            const len = path.getTotalLength();
            path.style.strokeDasharray = `${len}`;
            path.style.strokeDashoffset = `${len}`;

            path.animate([{ strokeDashoffset: len }, { strokeDashoffset: 0 }], {
                duration: durationPerStrokeMs,
                delay: cursor,
                easing: "ease-in-out",
                fill: "forwards",
            });

            cursor += durationPerStrokeMs + gapMs;
        });
    }, [colors, durationPerStrokeMs, gapMs]);

    useEffect(() => {
        runAnimation();
    }, [svg, runKey, runAnimation]);

    return (
        <div className="relative h-48 w-48">
            <button
                type="button"
                onClick={() => setRunKey((k) => k + 1)}
                className="absolute top-1 right-1 z-10 rounded-md bg-white/90 px-2 py-1 text-xs shadow hover:bg-white"
                title="Replay"
                aria-label="Replay"
            >
                ⟲
            </button>

            <div
                ref={rootRef}
                className="h-full w-full [&_svg]:h-full [&_svg]:w-full"
                dangerouslySetInnerHTML={{ __html: svg }}
            />
        </div>
    );
}
