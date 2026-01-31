import Empty from "@/components/common/empty";
import { TooltipCustom } from "@/components/mui-custom/tooltip.custom";
import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";
import LaunchIcon from "@mui/icons-material/Launch";
import { Button } from "@mui/material";
import FolderDeleteButton from "../features/folder.delete.button";

const FolderTable = ({ folders }: { folders: FolderResponse[] }) => {
    return (
        <div>
            {folders.length === 0 ? (
                <Empty text="Không có thư mục nào để hiển thị" />
            ) : (
                <table className="w-full">
                    <thead>
                        <tr className="font-semibold">
                            <td>STT</td>
                            <td>Tên thư mục</td>
                            <td>Chỉnh sửa lần cuối</td>
                            <td>Hành động</td>
                        </tr>
                    </thead>
                    <tbody>
                        {folders.map((folder, index) => {
                            return (
                                <tr key={folder.id}>
                                    <td className="py-2">{index + 1}</td>
                                    <td className="py-2">
                                        <Link
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
                                            className="hover:text-tc-highlight hover:underline"
                                        >
                                            {folder.folderName}
                                        </Link>
                                    </td>
                                    <td className="py-2">
                                        {folder.modifiedTime !== null
                                            ? folder.modifiedTime
                                            : folder.createdTime}
                                    </td>
                                    <td className="flex items-center gap-x-1 py-2">
                                        <FolderDeleteButton folder={folder} />
                                        <TooltipCustom title="Mở thư mục">
                                            <Link
                                                href={{
                                                    pathname:
                                                        "/your-folder/[slug]",
                                                    params: {
                                                        slug: slugifyText(
                                                            folder.folderName +
                                                                "-" +
                                                                folder.id,
                                                        ),
                                                    },
                                                }}
                                            >
                                                <Button
                                                    variant="text"
                                                    color="primary"
                                                    sx={{
                                                        width: "36px",
                                                        minWidth: "36px",
                                                    }}
                                                >
                                                    <LaunchIcon fontSize="small" />
                                                </Button>
                                            </Link>
                                        </TooltipCustom>
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default FolderTable;
