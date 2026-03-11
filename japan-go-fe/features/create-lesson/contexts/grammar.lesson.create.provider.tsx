"use client";
import React, { createContext, useContext, useState } from "react";
import { GRAMMAR_JLPT_LEVELS } from "@/features/create-lesson/constants/create.lesson.constants";
import { IGrammarJlptLevel } from "@/features/create-lesson/types/create.lesson.ui.type";

interface IGrammarLessonCreateProviderProps {
    errorMessage: string;
    setErrorMessage: React.Dispatch<React.SetStateAction<string>>;
    selectedLevel: IGrammarJlptLevel;
    setSelectedLevel: React.Dispatch<React.SetStateAction<IGrammarJlptLevel>>;
    files: File[];
    setFiles: React.Dispatch<React.SetStateAction<File[]>>;
}

const GrammarLessonCreateContext = createContext<
    IGrammarLessonCreateProviderProps | undefined
>(undefined);

export const GrammarLessonCreateProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [selectedLevel, setSelectedLevel] = useState(GRAMMAR_JLPT_LEVELS[0]);
    const [files, setFiles] = useState<File[]>([]);

    return (
        <GrammarLessonCreateContext.Provider
            value={{
                errorMessage,
                setErrorMessage,
                selectedLevel,
                setSelectedLevel,
                files,
                setFiles,
            }}
        >
            {children}
        </GrammarLessonCreateContext.Provider>
    );
};

export const useGrammarLessonCreate = () => {
    const context = useContext(GrammarLessonCreateContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
