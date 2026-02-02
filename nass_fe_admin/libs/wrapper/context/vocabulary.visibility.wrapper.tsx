"use client";
import {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useState,
} from "react";

interface IVocabularyVisibilityWrapperProps {
    showJapanese: boolean;
    setShowJapanese: Dispatch<SetStateAction<boolean>>;
    showReading: boolean;
    setShowReading: Dispatch<SetStateAction<boolean>>;
    showMeaning: boolean;
    setShowMeaning: Dispatch<SetStateAction<boolean>>;
}

const VocabularyVisibilityContext = createContext<
    IVocabularyVisibilityWrapperProps | undefined
>(undefined);

export const VocabularyVisibilityWrapper = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [showJapanese, setShowJapanese] = useState(true);
    const [showReading, setShowReading] = useState(true);
    const [showMeaning, setShowMeaning] = useState(true);

    return (
        <VocabularyVisibilityContext.Provider
            value={{
                showJapanese,
                setShowJapanese,
                showReading,
                setShowReading,
                showMeaning,
                setShowMeaning,
            }}
        >
            {children}
        </VocabularyVisibilityContext.Provider>
    );
};

export const useVocabularyVisibility = () => {
    const context = useContext(VocabularyVisibilityContext);
    if (!context) {
        throw new Error("wrapper error");
    }
    return context;
};
