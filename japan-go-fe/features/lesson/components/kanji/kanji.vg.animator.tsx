import ReplayIcon from "@mui/icons-material/Replay";
import { Box, Button } from "@mui/material";
import React, { useEffect, useRef } from "react";
import { useKanjiVgAnimation } from "@/features/lesson/hooks/use.kanji.vg.animation";

const KanjiVgAnimator = React.memo(function KanjiVgAnimator({
    kanjiVg,
    durationPerStroke = 250,
    durationBetweenEachStroke = 50,
}: {
    kanjiVg: string;
    durationPerStroke?: number;
    durationBetweenEachStroke?: number;
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

    return (
        <div className="border-bdc-primary relative flex h-max items-center justify-center rounded-md border">
            <Box
                ref={containerRef}
                sx={{
                    width: "100%",
                    height: "100%",
                    padding: "12px 12px 24px",
                    "& svg": { width: "192px", height: "192px" },
                }}
            />

            <Button
                variant="text"
                color="primary"
                sx={{
                    width: "32px",
                    minWidth: "32px",
                    height: "32px",
                    borderRadius: "50%",
                    position: "absolute",
                    top: "8px",
                    right: "8px",
                }}
                size="small"
                onClick={run}
            >
                <ReplayIcon fontSize="small" />
            </Button>
        </div>
    );
});

export default KanjiVgAnimator;
