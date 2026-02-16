import React from "react";
import { FolderResponse } from "@/types/api/responses/common.response";
import SingleFolder from "@/features/your-library/components/folder/single.folder";
import { useTranslations } from "next-intl";
import { Pagination } from "@mui/material";
import WrapBox from "@/components/ui/wrap.box";

const FolderList = ({ folders }: { folders: FolderResponse[] }) => {
    const t = useTranslations("Pages.yourLibrary.folder");
    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">{t("folderList")}</h1>
            <div className="flex flex-col gap-y-3">
                {folders.map((folder) => {
                    return <SingleFolder key={folder.id} folder={folder} />;
                })}
            </div>
            <div className="mt-5 flex justify-end">
                <Pagination
                    count={10}
                    shape="rounded"
                    color="primary"
                    showFirstButton
                    showLastButton
                />
            </div>
        </WrapBox>
    );
};

export default FolderList;
