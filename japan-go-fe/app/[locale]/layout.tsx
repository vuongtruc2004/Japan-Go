import AppThemeProvider from "@/components/providers/app.theme.provider";
import BProgressProvider from "@/components/providers/bprogress.provider";
import { routing } from "@/i18n/routing";
import { fontNotoSansJP, fontQuicksand } from "@/styles/font";
import { InitColorSchemeScript } from "@mui/material";
import { AppRouterCacheProvider } from "@mui/material-nextjs/v13-appRouter";
import "flag-icons/css/flag-icons.min.css";
import { hasLocale, NextIntlClientProvider } from "next-intl";
import { notFound } from "next/navigation";
import "./globals.css";
import { ToastContainer } from "react-toastify";

type Props = {
    children: React.ReactNode;
    params: Promise<{ locale: string }>;
};

export default async function LocaleLayout({ children, params }: Props) {
    const { locale } = await params;
    if (!hasLocale(routing.locales, locale)) {
        notFound();
    }

    return (
        <html
            lang={locale}
            data-locale={locale}
            className={`${fontQuicksand.variable} ${fontNotoSansJP.variable}`}
            suppressHydrationWarning
        >
            <body>
                <NextIntlClientProvider>
                    <AppRouterCacheProvider options={{ enableCssLayer: true }}>
                        <BProgressProvider>
                            <AppThemeProvider>
                                <InitColorSchemeScript
                                    attribute="class"
                                    defaultMode="light"
                                />
                                <main>{children}</main>
                                <ToastContainer />
                            </AppThemeProvider>
                        </BProgressProvider>
                    </AppRouterCacheProvider>
                </NextIntlClientProvider>
            </body>
        </html>
    );
}
