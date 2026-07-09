"use client";
import { styled, Tooltip, tooltipClasses, TooltipProps } from "@mui/material";

interface TooltipCustomProps extends TooltipProps {
    color?: string;
}

export const TooltipCustom = styled(
    ({ className, ...props }: TooltipCustomProps) => (
        <Tooltip {...props} arrow classes={{ popper: className }} />
    ),
)(({ color = "--color-bgc-highlight" }) => {
    const resolvedColor = color.startsWith("--") ? `var(${color})` : color;
    return {
        [`& .${tooltipClasses.arrow}`]: {
            color: resolvedColor,
        },
        [`& .${tooltipClasses.tooltip}`]: {
            backgroundColor: resolvedColor,
            fontWeight: "bold",
        },
    };
});

