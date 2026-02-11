import FolderEmpty from "@/features/your-library/components/folder.empty";
import { getAllFolders } from "@/lib/api/folder/folder";
import { getTranslations } from "next-intl/server";

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
    const folders = await getAllFolders();
    return (
        <div>
            {folders.length !== 0 ? (
                <FolderEmpty />
            ) : (
                folders.map((folder) => {
                    return <div key={folder.id}>{folder.folderName}</div>;
                })
            )}
        </div>
    );
};

export default FolderPage;
