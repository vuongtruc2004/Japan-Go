"use client";
import { styled, Tooltip, tooltipClasses, TooltipProps } from "@mui/material";

export const TooltipCustom = styled(({ className, ...props }: TooltipProps) => (
    <Tooltip {...props} arrow classes={{ popper: className }} />
))(() => ({
    [`& .${tooltipClasses.arrow}`]: {
        color: "var(--color-bgc-highlight)",
    },
    [`& .${tooltipClasses.tooltip}`]: {
        backgroundColor: "var(--color-bgc-highlight)",
        fontWeight: "bold",
    },
}));
