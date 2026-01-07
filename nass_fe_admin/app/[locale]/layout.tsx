import { routing } from "@/i18n/routing";
import BProgressWrapper from "@/libs/wrapper/common/bprogress/bprogress.wrapper";
import { notoSansJP, quicksand } from "@/libs/wrapper/common/theme/fonts";
import ThemeWrapper from "@/libs/wrapper/common/theme/theme.wrapper";
import { InitColorSchemeScript } from "@mui/material";
import { AppRouterCacheProvider } from "@mui/material-nextjs/v13-appRouter";
import "flag-icons/css/flag-icons.min.css";
import { hasLocale, NextIntlClientProvider } from "next-intl";
import { notFound } from "next/navigation";
import "./globals.css";

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
            className={`${quicksand.variable} ${notoSansJP.variable}`}
            suppressHydrationWarning
        >
            <body>
                <NextIntlClientProvider>
                    <AppRouterCacheProvider options={{ enableCssLayer: true }}>
                        <BProgressWrapper>
                            <ThemeWrapper>
                                <InitColorSchemeScript
                                    attribute="class"
                                    defaultMode="light"
                                />
                                <main>{children}</main>
                            </ThemeWrapper>
                        </BProgressWrapper>
                    </AppRouterCacheProvider>
                </NextIntlClientProvider>
            </body>
        </html>
    );
}
