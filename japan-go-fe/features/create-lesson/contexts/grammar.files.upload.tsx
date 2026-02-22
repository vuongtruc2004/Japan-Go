"use client";
import React, { createContext, useContext, useState } from "react";

interface IGrammarFilesUploadProviderProps {
    errorMessage: string;
    setErrorMessage: React.Dispatch<React.SetStateAction<string>>;
    files: File[];
    setFiles: React.Dispatch<React.SetStateAction<File[]>>;
}

const GrammarFilesUploadContext = createContext<
    IGrammarFilesUploadProviderProps | undefined
>(undefined);

export const GrammarFilesUploadProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [files, setFiles] = useState<File[]>([]);

    return (
        <GrammarFilesUploadContext.Provider
            value={{ errorMessage, setErrorMessage, files, setFiles }}
        >
            {children}
        </GrammarFilesUploadContext.Provider>
    );
};

export const useGrammarFilesUpload = () => {
    const context = useContext(GrammarFilesUploadContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
