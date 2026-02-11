import { createTheme } from "@mui/material";

export const muiTheme = createTheme({
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
                },
                error: {
                    main: "#e5383b"
                },
                text: {
                    primary: "#e5e5e5"
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
                },
                error: {
                    main: "#d90429"
                },
                text: {
                    primary: "#303030"
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
                    height: '36px'
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


