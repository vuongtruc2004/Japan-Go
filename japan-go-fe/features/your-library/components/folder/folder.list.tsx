import React from "react";
import { FolderResponse } from "@/types/api/responses/common.response";
import SingleFolder from "@/features/your-library/components/folder/single.folder";
import { useTranslations } from "next-intl";
import Empty from "@/components/ui/empty";
import { Button } from "@mui/material";
import FolderCreateButton from "@/components/domain/folder/folder.create.button";
import WrapBox from "@/components/ui/wrap.box";

const FolderList = ({ folders }: { folders: FolderResponse[] }) => {
    const t = useTranslations("Pages.yourLibrary.folder");

    if (!folders.length) {
        return (
            <WrapBox>
                <div className="flex flex-col items-center">
                    <Empty text={t("noFolders")} />
                    <FolderCreateButton
                        buttonElement={
                            <Button variant="contained" color="primary">
                                {t("createFolder")}
                            </Button>
                        }
                    />
                </div>
            </WrapBox>
        );
    }

    return (
        <WrapBox>
            <h1 className="mb-3 font-semibold">{t("folderList")}</h1>
            <div className="flex flex-col gap-y-3">
                {folders.map((folder) => {
                    return <SingleFolder key={folder.id} folder={folder} />;
                })}
            </div>
        </WrapBox>
    );
};

export default FolderList;
