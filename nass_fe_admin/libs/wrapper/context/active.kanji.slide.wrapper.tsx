"use client";
import {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useState,
} from "react";

interface IActiveKanjiSlideWrapperProps {
    activeIndex: number;
    setActiveIndex: Dispatch<SetStateAction<number>>;
    lesson: LessonDetailsResponse;
}

const ActiveKanjiSlideContext = createContext<
    IActiveKanjiSlideWrapperProps | undefined
>(undefined);

export const ActiveKanjiSlideWrapper = ({
    children,
    lesson,
}: {
    children: React.ReactNode;
    lesson: LessonDetailsResponse;
}) => {
    const [activeIndex, setActiveIndex] = useState<number>(0);

    return (
        <ActiveKanjiSlideContext.Provider
            value={{ activeIndex, setActiveIndex, lesson }}
        >
            {children}
        </ActiveKanjiSlideContext.Provider>
    );
};

export const useActiveKanjiSlide = () => {
    const context = useContext(ActiveKanjiSlideContext);
    if (!context) {
        throw new Error("wrapper error");
    }
    return context;
};
