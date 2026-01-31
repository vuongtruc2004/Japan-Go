import Empty from "@/components/common/empty";
import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import FolderMoreButton from "../features/folder.more.button";

const FolderList = ({ folders }: { folders: FolderResponse[] }) => {
    return (
        <div>
            {folders.length === 0 ? (
                <Empty text="Không có thư mục nào để hiển thị" />
            ) : (
                <div className="flex flex-col gap-y-3">
                    {folders.map((folder) => {
                        return (
                            <div
                                key={folder.id}
                                className="bg-bgc-page hover:bg-hbgc-highlight border-bdc-primary hover:border-bdc-primary flex cursor-pointer items-center justify-between rounded-md border pr-4 transition-all duration-150"
                            >
                                <Link
                                    className="flex-1 px-4 py-2"
                                    href={{
                                        pathname: "/your-folder/[slug]",
                                        params: {
                                            slug: slugifyText(
                                                folder.folderName +
                                                    "-" +
                                                    folder.id,
                                            ),
                                        },
                                    }}
                                >
                                    <p className="mb-1 text-sm font-semibold">
                                        0 mục
                                    </p>
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
                            </div>
                        );
                    })}
                </div>
            )}
        </div>
    );
};

export default FolderList;
