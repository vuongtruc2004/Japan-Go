"use client";
import { GROUP_KANJI_TAB } from "@/layouts/pages/kanji/models/kanji.tabs";
import { IKanjiTab } from "@/layouts/pages/kanji/models/kanji.type";
import {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useState,
} from "react";

interface IActiveKanjiTabWrapperProps {
    activeTab: IKanjiTab;
    setActiveTab: Dispatch<SetStateAction<IKanjiTab>>;
}

const ActiveKanjiTabContext = createContext<
    IActiveKanjiTabWrapperProps | undefined
>(undefined);

export const ActiveKanjiTabWrapper = ({
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
        throw new Error("wrapper error");
    }
    return context;
};
