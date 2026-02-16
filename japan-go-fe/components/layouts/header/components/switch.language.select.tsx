"use client";
import { usePathname, useRouter } from "@/i18n/navigation";
import { routing } from "@/i18n/routing";
import { MenuItem, Select, SelectChangeEvent } from "@mui/material";
import { useLocale, useTranslations } from "next-intl";
import { useParams } from "next/navigation";
import { LOCALES_META } from "../constants/constants";

const SwitchLanguageSelect = () => {
    const currentLocale = useLocale();
    const params = useParams();
    const pathname = usePathname();
    const router = useRouter();
    const t = useTranslations("AppHeader.switchLanguageButton.label");

    const handleChangeLanguage = (event: SelectChangeEvent) => {
        const nextLocale = event.target.value;
        router.replace(
            // @ts-expect-error -- TypeScript will validate that only known `params`
            { pathname, params },
            { locale: nextLocale },
        );
    };

    return (
        <Select
            color="success"
            onChange={handleChangeLanguage}
            defaultValue={currentLocale}
            size="small"
            sx={{
                height: "40px",
                fontSize: "14px",
            }}
        >
            {routing.locales.map((locale) => {
                const meta = LOCALES_META[locale];

                return (
                    <MenuItem key={locale} value={locale}>
                        <div className="flex items-center gap-x-2">
                            <span
                                className={`fi fi-${meta.flag} shadow-sm`}
                            ></span>
                            <span>{t(`${locale}`)}</span>
                        </div>
                    </MenuItem>
                );
            })}
        </Select>
    );
};

export default SwitchLanguageSelect;
