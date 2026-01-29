const FolderList = ({ folders }: { folders: FolderResponse[] }) => {
    return (
        <div>
            {folders.map((folder) => {
                return <div key={folder.id}>{folder.folderName}</div>;
            })}
        </div>
    );
};

export default FolderList;
