import React from "react";
import { IconButton, IconButtonProps } from "@mui/material";

type IconButtonCustomProps = IconButtonProps & {
    hasBgColor?: boolean;
};

const IconButtonCustom: React.FC<IconButtonCustomProps> = ({
    children,
    hasBgColor = true,
    sx,
    ...rest
}) => {
    return (
        <IconButton
            {...rest}
            sx={{
                width: "40px",
                height: "40px",
                ...(hasBgColor && {
                    bgcolor: "var(--color-bgc-page)",
                    "&:hover": { bgcolor: "var(--color-hbgc-highlight)" },
                }),
                color: "var(--color-tc-primary)",
                ...sx,
            }}
        >
            {children}
        </IconButton>
    );
};

export default IconButtonCustom;
