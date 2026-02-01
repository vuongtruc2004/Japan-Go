import FolderList from "@/layouts/pages/my-folder/components/folder.list";
import FolderPageHeader from "@/layouts/pages/my-folder/components/folder.page.header";
import { getAllFolders } from "@/libs/api/folder/folder";
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

const MyFolderPage = async () => {
    const folders = await getAllFolders();

    return (
        <div>
            <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
                <FolderPageHeader />
                <FolderList folders={folders} />
            </div>
        </div>
    );
};

export default MyFolderPage;
