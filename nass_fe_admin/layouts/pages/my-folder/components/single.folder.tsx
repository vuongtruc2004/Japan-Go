import DeleteOutlineIcon from "@mui/icons-material/DeleteOutline";
const SingleFolder = ({ folder }: { folder: FolderResponse }) => {
    return (
        <div className="hover:bg-hbgc-highlight border-bdc-primary hover:border-bdc-primary bg-bgc-page flex w-max cursor-pointer items-center justify-center rounded-md border px-4 py-2 transition-all duration-150">
            {folder.folderName}
            <DeleteOutlineIcon />
        </div>
    );
};

export default SingleFolder;
