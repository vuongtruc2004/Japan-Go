import FolderDetailsHeader from "@/layouts/pages/my-folder-details/components/folder.details.header";
import LessonList from "@/layouts/pages/my-folder-details/components/lesson.list";
import { getFolderById } from "@/libs/api/folder/folder";
import { FolderDetailsWrapper } from "@/libs/wrapper/context/folder.details.wrapper";
import { Metadata } from "next";

export async function generateMetadata({
    params,
}: {
    params: Promise<{ slug: string }>;
}): Promise<Metadata> {
    const slug = (await params).slug;
    const id = slug.split("-").pop() || "";
    const folder = await getFolderById(id);

    return {
        title: folder.folderName,
    };
}

const FolderDetailsPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const slug = (await params).slug;
    const id = slug.split("-").pop() || "";
    const folder = await getFolderById(id);

    return (
        <FolderDetailsWrapper folder={folder}>
            <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
                <FolderDetailsHeader />
                <LessonList />
            </div>
        </FolderDetailsWrapper>
    );
};

export default FolderDetailsPage;
