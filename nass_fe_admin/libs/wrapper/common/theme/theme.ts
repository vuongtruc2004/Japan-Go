'use client'
import { createTheme } from "@mui/material";

const theme = createTheme({
    colorSchemes: {
        dark: {
            palette: {
                primary: {
                    main: "#016630",
                },
                secondary: {
                    main: "#242322",
                },
                success: {
                    main: "#00c951",
                }
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
                }
            },
        },
    },
    cssVariables: {
        colorSchemeSelector: "class",
    },
    typography: {
        fontFamily: "var(--app-font)"
    },
    components: {
        MuiButton: {
            styleOverrides: {
                root: {
                    textTransform: "none",
                    borderRadius: "6px",
                },
                contained: {
                    color: "white",
                },
                text: {
                    ':hover': {
                        backgroundColor: 'var(--color-hbgc-app)'
                    }
                },
                outlined: {
                }
            },
        }
    },
});

export default theme;
