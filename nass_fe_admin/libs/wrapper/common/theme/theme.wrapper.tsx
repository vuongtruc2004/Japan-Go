import { CssBaseline, ThemeProvider } from "@mui/material";
import theme from "./theme";

const ThemeWrapper = ({ children }: { children: React.ReactNode }) => {
    return (
        <ThemeProvider
            theme={theme}
            defaultMode="light"
            disableTransitionOnChange
        >
            <CssBaseline />
            {children}
        </ThemeProvider>
    );
};

export default ThemeWrapper;
