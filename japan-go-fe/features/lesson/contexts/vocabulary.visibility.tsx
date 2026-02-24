"use client";
import React, {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useMemo,
    useState,
} from "react";

interface IVocabularyVisibilityProviderProps {
    showJapanese: boolean;
    setShowJapanese: Dispatch<SetStateAction<boolean>>;
    showSinoVietnamese: boolean;
    setShowSinoVietnamese: Dispatch<SetStateAction<boolean>>;
    showReading: boolean;
    setShowReading: Dispatch<SetStateAction<boolean>>;
    showMeaning: boolean;
    setShowMeaning: Dispatch<SetStateAction<boolean>>;
    showNote: boolean;
    setShowNote: Dispatch<SetStateAction<boolean>>;
}

const VocabularyVisibilityContext = createContext<
    IVocabularyVisibilityProviderProps | undefined
>(undefined);

export const VocabularyVisibilityProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [showJapanese, setShowJapanese] = useState(true);
    const [showSinoVietnamese, setShowSinoVietnamese] = useState(true);
    const [showReading, setShowReading] = useState(true);
    const [showMeaning, setShowMeaning] = useState(true);
    const [showNote, setShowNote] = useState(true);

    const value = useMemo(
        () => ({
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
        }),
        [showJapanese, showSinoVietnamese, showReading, showMeaning, showNote],
    );

    return (
        <VocabularyVisibilityContext.Provider value={value}>
            {children}
        </VocabularyVisibilityContext.Provider>
    );
};

export const useVocabularyVisibility = () => {
    const context = useContext(VocabularyVisibilityContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
