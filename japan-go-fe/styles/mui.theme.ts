import { createTheme } from "@mui/material";

export const muiTheme = createTheme({
    colorSchemes: {
        dark: {
            palette: {
                primary: {
                    main: "#16a34a",
                    light: "#22c55e",
                    dark: "#166534",
                    contrastText: "#f0fdf4",
                },
                secondary: {
                    main: "#1f1f1f",
                    light: "#242424",
                    dark: "#141414",
                    contrastText: "#f0f0f0",
                },
                success: {
                    main: "#16a34a",
                    light: "#22c55e",
                    dark: "#166534",
                    contrastText: "#f0fdf4",
                },
                error: {
                    main: "#f87171",
                    light: "#fca5a5",
                    dark: "#dc2626",
                },
                warning: {
                    main: "#fbbf24",
                    light: "#fcd34d",
                    dark: "#d97706",
                },
                text: {
                    primary: "#f0f0f0",
                    secondary: "#898989",
                    disabled: "#484848",
                },
                background: {
                    default: "#0a0a0a",
                    paper: "#141414",
                },
                divider: "#242424",
                action: {
                    hover: "rgba(255, 255, 255, 0.06)",
                    selected: "rgba(255, 255, 255, 0.10)",
                    focus: "rgba(255, 255, 255, 0.14)",
                    disabledBackground: "rgba(255, 255, 255, 0.04)",
                },
            },
        },
        light: {
            palette: {
                primary: {
                    main: "#175a3a",
                },
                secondary: {
                    main: "#a5a5a5",
                },
                success: {
                    main: "#175a3a",
                },
                error: {
                    main: "#d90429",
                },
                text: {
                    primary: "#303030",
                },
            },
        },
    },
    cssVariables: {
        colorSchemeSelector: "class",
    },
    typography: {
        fontFamily: "var(--app-font)",
    },
    components: {
        MuiButton: {
            styleOverrides: {
                root: {
                    textTransform: "none",
                    borderRadius: "6px",
                    height: "40px",
                    minWidth: "max-content",
                    fontWeight: 500,
                    transition:
                        "background-color 150ms ease, box-shadow 150ms ease, border-color 150ms ease",
                },
                contained: {
                    color: "#f0fdf4",
                    boxShadow: "none",
                    "&:hover": {
                        boxShadow: "none",
                    },
                },
                // outlined: NO border overrides here.
                // MUI resolves border color from palette[color].main automatically.
                // Overriding it here would break per-color consistency
                // (e.g. error button would get a green border on hover).
                outlined: {
                    "&:hover": {
                        // Only override bg — border is handled by MUI palette per color
                        backgroundColor: "rgba(var(--mui-palette-primary-mainChannel) / 0.08)",
                    },
                },
                text: {
                    "&:hover": {
                        // Use MUI's palette-driven channel so each color tints correctly
                        backgroundColor: "rgba(var(--mui-palette-primary-mainChannel) / 0.08)",
                    },
                },
            },
        },
        MuiCard: {
            styleOverrides: {
                root: {
                    backgroundImage: "none",
                    border: "1px solid var(--color-bdc-primary)",
                    borderRadius: "10px",
                    boxShadow: "none",
                },
            },
        },
        MuiOutlinedInput: {
            styleOverrides: {
                root: {
                    borderRadius: "6px",
                    "& .MuiOutlinedInput-notchedOutline": {
                        borderColor: "var(--color-bdc-muted)",
                    },
                    "&:hover .MuiOutlinedInput-notchedOutline": {
                        borderColor: "var(--color-tc-highlight)",
                    },
                    "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
                        borderColor: "var(--color-tc-highlight)",
                        borderWidth: "1.5px",
                    },
                },
            },
        },
        MuiChip: {
            styleOverrides: {
                root: {
                    borderRadius: "6px",
                    fontWeight: 500,
                },
                outlined: {
                    borderColor: "var(--color-bdc-muted)",
                },
            },
        },
        MuiDivider: {
            styleOverrides: {
                root: {
                    borderColor: "var(--color-bdc-primary)",
                },
            },
        },
        MuiTooltip: {
            styleOverrides: {
                tooltip: {
                    backgroundColor: "#1f1f1f",
                    color: "#f0f0f0",
                    fontSize: "0.75rem",
                    border: "1px solid #383838",
                    borderRadius: "6px",
                },
                arrow: {
                    color: "#1f1f1f",
                },
            },
        },
    },
});
