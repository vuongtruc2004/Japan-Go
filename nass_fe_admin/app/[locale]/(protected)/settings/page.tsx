import { getTranslations } from "next-intl/server";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "AppSidebar.links" });

    return {
        title: t("settings"),
    };
}

const SettingsPage = () => {
    return <div>SettingsPage</div>;
};

export default SettingsPage;
