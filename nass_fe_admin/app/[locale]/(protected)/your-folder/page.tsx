import FolderList from "@/layouts/pages/my-folder/components/folder.list";
import FolderPageHeader from "@/layouts/pages/my-folder/components/folder.page.header";
import { sendRequest } from "@/utils/fetch.api";
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
    const response = await sendRequest<ApiResponse<FolderResponse[]>>({
        url: "/folder/all",
    });
    return (
        <div>
            <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
                <FolderPageHeader />
                <FolderList folders={response.data} />
            </div>
        </div>
    );
};

export default MyFolderPage;
