import ReplayIcon from "@mui/icons-material/Replay";
import { Box, Button } from "@mui/material";
import { useCallback, useEffect, useRef } from "react";
import { STROKE_COLORS } from "../models/stroke.colors";

const KanjiVgDraw = ({
    svg,
    durationPerStroke = 250,
    durationBetweenEachStroke = 50,
}: {
    svg: string;
    durationPerStroke?: number;
    durationBetweenEachStroke?: number;
}) => {
    const containerRef = useRef<HTMLDivElement>(null);

    const runAnimation = useCallback(async () => {
        if (!containerRef.current) return;

        const svgElement: SVGSVGElement | null =
            containerRef.current.querySelector("svg");

        if (!svgElement) return;

        const pathElements: SVGPathElement[] = Array.from(
            svgElement.querySelectorAll("path"),
        );
        const numberElements: SVGTextElement[] = Array.from(
            svgElement.querySelectorAll("text"),
        );

        // ===== RESET =====
        pathElements.forEach((path) => {
            const length = path.getTotalLength();

            path.style.stroke = "#000";
            path.style.strokeWidth = "3";
            path.style.fill = "none";
            path.style.strokeDasharray = `${length}`;
            path.style.strokeDashoffset = `${length}`;
            path.style.transition = "none";
        });

        numberElements.forEach((text) => {
            text.style.opacity = "0";
            text.style.fill = "#000";
        });

        // force reflow để browser apply reset
        svgElement.getBoundingClientRect();

        // ===== ANIMATE STROKES =====
        for (let i = 0; i < pathElements.length; i++) {
            const path = pathElements[i];
            const text = numberElements[i];

            const color = STROKE_COLORS[i % STROKE_COLORS.length];

            path.style.stroke = color;
            path.style.transition = `stroke-dashoffset ${durationPerStroke}ms linear`;

            if (text) {
                text.style.opacity = "1";
                text.style.fill = color;
            }

            requestAnimationFrame(() => {
                path.style.strokeDashoffset = "0";
            });

            await new Promise((resolve) =>
                setTimeout(
                    resolve,
                    durationPerStroke + durationBetweenEachStroke,
                ),
            );
        }
    }, [durationBetweenEachStroke, durationPerStroke]);

    useEffect(() => {
        runAnimation();
    }, [svg, runAnimation]);

    return (
        <div className="relative flex items-center justify-center">
            <Box
                ref={containerRef}
                sx={{
                    width: "100%",
                    height: "100%",
                    padding: "12px 12px 24px",
                    "& svg": {
                        width: "192px",
                        height: "192px",
                    },
                }}
                dangerouslySetInnerHTML={{ __html: svg }}
            />

            <Button
                variant="text"
                color="primary"
                sx={{
                    width: "36px",
                    minWidth: "36px",
                    borderRadius: "50%",
                    position: "absolute",
                    top: "8px",
                    right: "8px",
                }}
                size="small"
                onClick={runAnimation}
            >
                <ReplayIcon fontSize="small" />
            </Button>
        </div>
    );
};

export default KanjiVgDraw;
