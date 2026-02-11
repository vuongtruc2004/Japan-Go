"use client";
import { Link } from "@/i18n/navigation";
import { Box, Tab, Tabs } from "@mui/material";
import { useTranslations } from "next-intl";
import { useState } from "react";
import { LIBRARY_TABS } from "../constants/constants";

const LibraryTabs = () => {
    const t = useTranslations();
    const [value, setValue] = useState(0);

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (
        <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
            <h1 className="mb-3 font-semibold">
                {t("Pages.yourLibrary.title")}
            </h1>

            <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
                <Tabs value={value} onChange={handleChange}>
                    {LIBRARY_TABS.map((tab) => {
                        return (
                            <Tab
                                key={tab.id}
                                sx={{
                                    textTransform: "none",
                                    padding: 0,
                                    minWidth: "max-content",
                                    marginRight: "24px",
                                    fontWeight: "600",
                                    color: "var(--color-tc-muted)",
                                    ":hover": {
                                        color: "var(--color-tc-highlight)",
                                    },
                                }}
                                label={
                                    <Link
                                        href={tab.link}
                                        className="flex h-full w-full items-center justify-center"
                                    >
                                        {t(tab.nameKey)}
                                    </Link>
                                }
                            />
                        );
                    })}
                </Tabs>
            </Box>
        </div>
    );
};

export default LibraryTabs;

