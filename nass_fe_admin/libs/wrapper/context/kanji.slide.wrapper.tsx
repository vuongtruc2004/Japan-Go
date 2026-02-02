"use client";
import {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useEffect,
    useState,
} from "react";

interface IKanjiSlideWrapperProps {
    activeSlideIndex: number;
    setActiveSlideIndex: Dispatch<SetStateAction<number>>;
    lesson: LessonDetailsResponse;
}

const KanjiSlideContext = createContext<IKanjiSlideWrapperProps | undefined>(
    undefined,
);

export const KanjiSlideWrapper = ({
    children,
    lesson,
}: {
    children: React.ReactNode;
    lesson: LessonDetailsResponse;
}) => {
    const [activeSlideIndex, setActiveSlideIndex] = useState(0);
    const totalSlides = lesson.kanjiLesson.kanjiPages.length;

    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            if (e.key === "ArrowLeft") {
                setActiveSlideIndex((prev) =>
                    prev === 0 ? totalSlides - 1 : prev - 1,
                );
            }

            if (e.key === "ArrowRight") {
                setActiveSlideIndex((prev) =>
                    prev === totalSlides - 1 ? 0 : prev + 1,
                );
            }
        };

        window.addEventListener("keydown", handleKeyDown);

        return () => {
            window.removeEventListener("keydown", handleKeyDown);
        };
    }, [totalSlides]);

    return (
        <KanjiSlideContext.Provider
            value={{
                activeSlideIndex,
                setActiveSlideIndex,
                lesson,
            }}
        >
            {children}
        </KanjiSlideContext.Provider>
    );
};

export const useKanjiSlide = () => {
    const context = useContext(KanjiSlideContext);
    if (!context) {
        throw new Error("wrapper error");
    }
    return context;
};
