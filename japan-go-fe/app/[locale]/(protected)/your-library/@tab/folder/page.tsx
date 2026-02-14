import { getTranslations } from "next-intl/server";
import { getAllFolders } from "@/services/folder.service";
import Empty from "@/components/ui/empty";
import FolderList from "@/features/your-library/components/folder/folder.list";
import FolderSearchBox from "@/features/your-library/components/folder/folder.search.box";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "Common" });

    return {
        title: t("folder.title"),
    };
}

const FolderPage = async () => {
    const t = await getTranslations("Pages.yourLibrary");
    const folders = await getAllFolders();
    return (
        <>
            {folders.length === 0 ? (
                <Empty text={t("folder.noFolders")} />
            ) : (
                <>
                    <FolderSearchBox />
                    <FolderList folders={folders} />
                </>
            )}
        </>
    );
};

export default FolderPage;
