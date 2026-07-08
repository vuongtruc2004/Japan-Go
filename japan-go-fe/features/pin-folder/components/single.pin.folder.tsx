import React from "react";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { Link, usePathname } from "@/i18n/navigation";
import { useSidebarCollapse } from "@/layouts/sidebar/context/sidebar.collapse";
import { FolderResponse } from "@/types/api/responses/common.response";
import { slugifyText } from "@/utils/slugify.text";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import { useParams, useSearchParams } from "next/navigation";
import { SidebarEffectClassName } from "@/layouts/sidebar/types/sidebar.ui.type";

const SinglePinFolder = ({ folder }: { folder: FolderResponse }) => {
    const { isCollapse } = useSidebarCollapse();
    const pathname = usePathname();
    const searchParams = useSearchParams();

    const slug = slugifyText(folder.folderName + "-" + folder.id);

    const params = useParams<{ locale?: string; slug?: string }>();
    const paramSlug = params.slug;
    const queryFolderId = searchParams.get("folderId");

    const isActive =
        (slug === paramSlug && pathname.startsWith("/your-library/folder")) ||
        (pathname.startsWith("/lesson/") && queryFolderId === String(folder.id));

    const linkEffectClass = isActive
        ? SidebarEffectClassName.ACTIVE
        : SidebarEffectClassName.MUTED;

    return (
        <TooltipCustom
            title={isCollapse ? folder.folderName : ""}
            placement="right"
            arrow
        >
            <Link
                href={{
                    pathname: "/your-library/folder/[slug]",
                    params: {
                        slug,
                    },
                }}
                className={`${isCollapse ? "w-10" : "w-50"} flex h-10 shrink-0 items-center overflow-hidden rounded-md pl-2 transition-all duration-150 ${linkEffectClass}`}
            >
                <span className="flex h-10 items-center justify-center">
                    <FolderOutlinedIcon />
                </span>
                <p
                    className={`text-sm font-semibold whitespace-nowrap ${isCollapse && "hidden"} ml-4`}
                >
                    {folder.folderName}
                </p>
            </Link>
        </TooltipCustom>
    );
};

export default SinglePinFolder;
