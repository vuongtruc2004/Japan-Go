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
        title: t("your-folder"),
    };
}

const FolderDetailsPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const slug = (await params).slug;
    const id = slug.split("-").pop() || "";

    return <div>{id}</div>;
};

export default FolderDetailsPage;
