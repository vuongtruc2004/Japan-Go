import VisibilityOffOutlinedIcon from "@mui/icons-material/VisibilityOffOutlined";
import VisibilityOutlinedIcon from "@mui/icons-material/VisibilityOutlined";
import { useVocabularyVisibilityToggles } from "../hooks/use.vocabulary.visibility.toggles";

const VocabularyVisibilityButtons = () => {
    const vocabularyVisibilityToggles = useVocabularyVisibilityToggles();
    return (
        <div className="mb-3 flex items-center justify-end gap-x-3">
            {vocabularyVisibilityToggles.map((toggle) => {
                return (
                    <div
                        key={toggle.id}
                        onClick={() => toggle.setVisible((prev) => !prev)}
                        className="hover:bg-hbgc-highlight border-bdc-primary hover:border-bdc-primary bg-bgc-page flex h-9 w-max cursor-pointer items-center justify-center gap-x-3 rounded-md border px-3 text-sm font-semibold transition-all duration-150"
                    >
                        {toggle.visible ? (
                            <>
                                <VisibilityOutlinedIcon fontSize="small" />
                                {toggle.hideLabel}
                            </>
                        ) : (
                            <>
                                <VisibilityOffOutlinedIcon fontSize="small" />
                                {toggle.showLabel}
                            </>
                        )}
                    </div>
                );
            })}
        </div>
    );
};

export default VocabularyVisibilityButtons;
