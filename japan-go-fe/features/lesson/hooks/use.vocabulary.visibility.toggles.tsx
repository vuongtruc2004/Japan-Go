import { IVocabularyVisibilityToggleConfig } from "@/features/lesson/types/lesson.ui.type";
import { useVocabularyVisibility } from "@/features/lesson/contexts/vocabulary.visibility";
import { DecoratorClassName } from "@/types/enums/share.enum";

export const useVocabularyVisibilityToggles =
    (): IVocabularyVisibilityToggleConfig[] => {
        const {
            showJapanese,
            setShowJapanese,
            showSinoVietnamese,
            setShowSinoVietnamese,
            showReading,
            setShowReading,
            showMeaning,
            setShowMeaning,
            showNote,
            setShowNote,
        } = useVocabularyVisibility();

        return [
            {
                id: "japanese",
                visible: showJapanese,
                setVisible: setShowJapanese,
                showLabelKey: "japanese",
                hideLabelKey: "japanese",
                showClassName: DecoratorClassName.GREEN,
            },
            {
                id: "sino-vietnamese",
                visible: showSinoVietnamese,
                setVisible: setShowSinoVietnamese,
                showLabelKey: "sinoVietnamese",
                hideLabelKey: "sinoVietnamese",
                showClassName: DecoratorClassName.BLUE,
            },
            {
                id: "reading",
                visible: showReading,
                setVisible: setShowReading,
                showLabelKey: "reading",
                hideLabelKey: "reading",
                showClassName: DecoratorClassName.YELLOW,
            },
            {
                id: "meaning",
                visible: showMeaning,
                setVisible: setShowMeaning,
                showLabelKey: "meaning",
                hideLabelKey: "meaning",
                showClassName: DecoratorClassName.ORANGE,
            },
            {
                id: "note",
                visible: showNote,
                setVisible: setShowNote,
                showLabelKey: "note",
                hideLabelKey: "note",
                showClassName: DecoratorClassName.RED,
            },
        ];
    };
