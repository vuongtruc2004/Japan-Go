import { useVocabularyVisibility } from "@/libs/wrapper/context/vocabulary.visibility.wrapper";
import { VisibilityToggleConfig } from "../models/lesson.kanji.type";

export const useVocabularyVisibilityToggles = (): VisibilityToggleConfig[] => {
    const {
        showJapanese,
        setShowJapanese,
        showReading,
        setShowReading,
        showMeaning,
        setShowMeaning,
    } = useVocabularyVisibility();

    return [
        {
            id: "japanese",
            visible: showJapanese,
            setVisible: setShowJapanese,
            showLabel: "Hiện chữ Hán",
            hideLabel: "Ẩn chữ Hán",
        },
        {
            id: "reading",
            visible: showReading,
            setVisible: setShowReading,
            showLabel: "Hiện phiên âm",
            hideLabel: "Ẩn phiên âm",
        },
        {
            id: "meaning",
            visible: showMeaning,
            setVisible: setShowMeaning,
            showLabel: "Hiện nghĩa",
            hideLabel: "Ẩn nghĩa",
        },
    ];
};
