import { getTranslations } from "next-intl/server";
import { getAllFolders } from "@/services/folder.service";
import FolderList from "@/features/your-library/components/folder/folder.list";
import FolderSearchBox from "@/features/your-library/components/folder/folder.search.box";
import React from "react";

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
        <>
            {folders.length > 0 && <FolderSearchBox />}

            <FolderList folders={folders} />
        </>
    );
};

export default FolderPage;
