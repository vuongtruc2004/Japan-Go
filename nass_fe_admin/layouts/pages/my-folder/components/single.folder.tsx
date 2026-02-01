import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import FolderMoreButton from "../features/folder.more.button";

const SingleFolder = ({ folder }: { folder: FolderResponse }) => {
    return (
        <div className="bg-bgc-page border-bdc-primary group relative flex cursor-pointer items-center justify-between rounded-md border pr-4 transition-all duration-150">
            <Link
                className="flex-1 px-4 py-2"
                href={{
                    pathname: "/your-folder/[slug]",
                    params: {
                        slug: slugifyText(folder.folderName + "-" + folder.id),
                    },
                }}
            >
                <p className="mb-1 text-sm font-semibold">0 mục</p>
                <div className="flex items-center gap-x-3">
                    <FolderOutlinedIcon
                        sx={{
                            color: "var(--color-tc-muted)",
                        }}
                    />
                    <p className="text-tc-primary font-semibold">
                        {folder.folderName}
                    </p>
                </div>
            </Link>
            <FolderMoreButton folder={folder} />
            <div className="bg-bgc-page group-hover:bg-bgc-highlight absolute bottom-0 left-0 h-1 w-full rounded-br-md rounded-bl-md transition-all duration-150" />
        </div>
    );
};

export default SingleFolder;
