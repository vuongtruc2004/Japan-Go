import React from "react";
import { getFolderById } from "@/services/folder.service";
import { Metadata } from "next";
import FolderDetails from "@/features/your-library/components/folder/folder.details";

const getFolderFromParams = async (params: Promise<{ slug: string }>) => {
    const { slug } = await params;
    const id = slug.split("-").pop();
    if (!id) {
        throw new Error("invalidPath");
    }
    return getFolderById(id);
};

export async function generateMetadata({
    params,
}: {
    params: Promise<{ slug: string }>;
}): Promise<Metadata> {
    const folder = await getFolderFromParams(params);
    return {
        title: folder.folderName,
    };
}

const FolderDetailsPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const folder = await getFolderFromParams(params);
    return (
        <div>
            <FolderDetails folder={folder} />
        </div>
    );
};

export default FolderDetailsPage;
