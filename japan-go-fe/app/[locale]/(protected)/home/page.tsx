import { getTranslations } from "next-intl/server";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "Common.links" });

    return {
        title: t("home"),
    };
}

const HomePage = () => {
    return <div className="h-screen">Trang chủ</div>;
};

export default HomePage;
