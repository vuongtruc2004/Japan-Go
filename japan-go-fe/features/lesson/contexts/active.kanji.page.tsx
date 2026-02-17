"use client";
import React, {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useState,
} from "react";

interface IActiveKanjiPageProviderProps {
    activeIndex: number;
    setActiveIndex: Dispatch<SetStateAction<number>>;
}

const ActiveKanjiPageContext = createContext<
    IActiveKanjiPageProviderProps | undefined
>(undefined);

export const ActiveKanjiPageProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const [activeIndex, setActiveIndex] = useState<number>(0);
    return (
        <ActiveKanjiPageContext.Provider
            value={{ activeIndex, setActiveIndex }}
        >
            {children}
        </ActiveKanjiPageContext.Provider>
    );
};

export const useActiveKanjiPage = () => {
    const context = useContext(ActiveKanjiPageContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
