"use client";
import React, { createContext, useContext, useState } from "react";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";

interface IKanjiDataProviderProps {
    kanjiPages: KanjiPageRequest[];
    setKanjiPages: React.Dispatch<React.SetStateAction<KanjiPageRequest[]>>;
}

const KanjiDataContext = createContext<IKanjiDataProviderProps | undefined>(
    undefined,
);

export const KanjiDataProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [kanjiPages, setKanjiPages] = useState<KanjiPageRequest[]>([]);
    return (
        <KanjiDataContext.Provider value={{ kanjiPages, setKanjiPages }}>
            {children}
        </KanjiDataContext.Provider>
    );
};

export const useKanjiData = () => {
    const context = useContext(KanjiDataContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
