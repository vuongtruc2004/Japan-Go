import React from "react";
import { FolderResponse } from "@/types/api/responses/common.response";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import { useTranslations } from "next-intl";
import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";

const SingleFolder = ({ folder }: { folder: FolderResponse }) => {
    const t = useTranslations("Pages.yourLibrary.folder");

    return (
        <Link
            href={{
                pathname: "/your-library/folder/[slug]",
                params: {
                    slug: slugifyText(folder.folderName + "-" + folder.id),
                },
            }}
            className="border-bdc-muted group relative rounded-md border px-4 py-2"
        >
            <p className="text-tc-muted mb-1 text-sm font-semibold">
                {t("lessonCount", { lessonCount: folder.lessonCount })}
            </p>
            <div className="flex items-center gap-x-3">
                <FolderOutlinedIcon fontSize="small" />
                <p className="font-semibold">{folder.folderName}</p>
            </div>
            <div className="bg-bgc-highlight invisible absolute bottom-0 left-0 h-1 w-full rounded-b-md group-hover:visible" />
        </Link>
    );
};

export default SingleFolder;
