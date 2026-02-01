import Empty from "@/components/common/empty";
import SingleFolder from "./single.folder";

const FolderList = ({ folders }: { folders: FolderResponse[] }) => {
    return (
        <div>
            {folders.length === 0 ? (
                <Empty text="Không có thư mục nào để hiển thị" />
            ) : (
                <div className="flex flex-col gap-y-3">
                    {folders.map((folder) => {
                        return <SingleFolder key={folder.id} folder={folder} />;
                    })}
                </div>
            )}
        </div>
    );
};

export default FolderList;
