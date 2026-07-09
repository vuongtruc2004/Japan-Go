import ReplayIcon from "@mui/icons-material/Replay";
import { Box, Button } from "@mui/material";
import React, { useEffect, useRef } from "react";
import { useKanjiVgAnimation } from "@/features/lesson/hooks/use.kanji.vg.animation";

const KanjiVgAnimator = React.memo(function KanjiVgAnimator({
    kanjiVg,
    durationPerStroke = 500,
    durationBetweenEachStroke = 100,
    size = 192,
    className,
}: {
    kanjiVg: string;
    durationPerStroke?: number;
    durationBetweenEachStroke?: number;
    size?: number;
    className?: string;
}) {
    const containerRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        if (!containerRef.current) return;
        containerRef.current.innerHTML = kanjiVg;
    }, [kanjiVg]);

    const { run } = useKanjiVgAnimation({
        containerRef,
        kanjiVg,
        durationBetweenEachStroke,
        durationPerStroke,
    });

    const replayBtnSize = Math.max(20, Math.round(size * 0.16));

    return (
        <div 
            className={`border-bdc-primary relative flex items-center justify-center rounded-md border bg-bgc-app dark:bg-zinc-950 shrink-0 ${className || ""}`}
            style={className ? undefined : { width: `${size}px`, height: `${size}px` }}
            onClick={(e) => e.stopPropagation()}
        >
            <Box
                ref={containerRef}
                sx={{
                    width: "100%",
                    height: "100%",
                    padding: `${Math.round(size * 0.06)}px ${Math.round(size * 0.06)}px ${Math.round(size * 0.12)}px`,
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    "& svg": { 
                        width: "100%", 
                        height: "100%",
                        display: "block"
                    },
                }}
            />

            {size >= 80 && (
                <Button
                    variant="text"
                    color="primary"
                    sx={{
                        width: `${replayBtnSize}px`,
                        minWidth: `${replayBtnSize}px`,
                        height: `${replayBtnSize}px`,
                        borderRadius: "50%",
                        position: "absolute",
                        top: "4px",
                        right: "4px",
                        padding: 0,
                    }}
                    size="small"
                    onClick={run}
                >
                    <ReplayIcon sx={{ fontSize: `${Math.max(12, Math.round(size * 0.09))}px` }} />
                </Button>
            )}
        </div>
    );
});

export default KanjiVgAnimator;
