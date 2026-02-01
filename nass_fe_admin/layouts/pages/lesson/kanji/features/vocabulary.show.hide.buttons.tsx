import VisibilityOutlinedIcon from "@mui/icons-material/VisibilityOutlined";
const VocabularyShowHideButtons = () => {
    return (
        <div className="flex items-center justify-end gap-x-3">
            <div className="border-bdc-primary flex cursor-pointer items-center gap-x-3 rounded-md border px-3 py-2">
                <VisibilityOutlinedIcon />
                Hiện chữ Hán
            </div>
            <div className="border-bdc-primary flex cursor-pointer items-center gap-x-3 rounded-md border px-3 py-2">
                <VisibilityOutlinedIcon />
                Hiện phiên âm
            </div>
            <div className="border-bdc-primary flex cursor-pointer items-center gap-x-3 rounded-md border px-3 py-2">
                <VisibilityOutlinedIcon />
                Hiện nghĩa
            </div>
        </div>
    );
};

export default VocabularyShowHideButtons;
