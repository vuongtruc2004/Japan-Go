"use client";
import React, {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useState,
} from "react";
import { GROUP_KANJI_TAB } from "../constants/kanji.constants";
import { IKanjiTab } from "../types/kanji.ui.type";

interface IActiveKanjiTabProviderProps {
    activeTab: IKanjiTab;
    setActiveTab: Dispatch<SetStateAction<IKanjiTab>>;
}

const ActiveKanjiTabContext = createContext<
    IActiveKanjiTabProviderProps | undefined
>(undefined);

export const ActiveKanjiTabProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [activeTab, setActiveTab] = useState<IKanjiTab>(
        GROUP_KANJI_TAB[0].kanjiTabs[0],
    );
    return (
        <ActiveKanjiTabContext.Provider value={{ activeTab, setActiveTab }}>
            {children}
        </ActiveKanjiTabContext.Provider>
    );
};

export const useActiveKanjiTab = () => {
    const context = useContext(ActiveKanjiTabContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
