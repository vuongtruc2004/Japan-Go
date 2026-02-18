import React from "react";
import { Button, ButtonProps } from "@mui/material";

type ButtonCustomProps = ButtonProps & {
    hasBgColor?: boolean;
};

const ButtonCustom: React.FC<ButtonCustomProps> = ({
    children,
    hasBgColor = true,
    sx,
    ...rest
}) => {
    return (
        <Button
            {...rest}
            sx={{
                height: "40px",
                ...(hasBgColor && {
                    bgcolor: "var(--color-bgc-page)",
                    "&:hover": { bgcolor: "var(--color-hbgc-highlight)" },
                }),
                columnGap: "8px",
                paddingInline: "12px",
                borderRadius: "50px",
                color: "var(--color-tc-primary)",
                ...sx,
            }}
        >
            {children}
        </Button>
    );
};

export default ButtonCustom;
