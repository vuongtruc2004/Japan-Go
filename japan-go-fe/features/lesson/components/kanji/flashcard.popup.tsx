"use client";
import React, { useState, useEffect, useCallback } from "react";
import { Modal } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import CheckIcon from "@mui/icons-material/Check";
import UndoIcon from "@mui/icons-material/Undo";
import ShuffleIcon from "@mui/icons-material/Shuffle";
import SwapHorizIcon from "@mui/icons-material/SwapHoriz";
import SchoolOutlinedIcon from "@mui/icons-material/SchoolOutlined";
import OpenInNewIcon from "@mui/icons-material/OpenInNew";
import ReplayIcon from "@mui/icons-material/Replay";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { VocabularyResponse } from "@/types/api/responses/vocabulary.response";
import KanjiVgAnimator from "@/features/lesson/components/kanji/kanji.vg.animator";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";

interface FlashcardPopupProps {
    open: boolean;
    onClose: () => void;
    lesson: LessonResponse;
}

const FlashcardPopup = ({ open, onClose, lesson }: FlashcardPopupProps) => {
    // Gather all vocabularies across all kanji pages of this lesson
    const allVocabs = React.useMemo(() => {
        if (!lesson.kanjiLesson?.kanjiPages) return [];
        return lesson.kanjiLesson.kanjiPages.flatMap((page) => page.vocabularies);
    }, [lesson]);

    const [currentRoundVocabs, setCurrentRoundVocabs] = useState<VocabularyResponse[]>([]);
    const [nextRoundVocabs, setNextRoundVocabs] = useState<VocabularyResponse[]>([]);
    const [currentIndex, setCurrentIndex] = useState<number>(0);
    const [isFlipped, setIsFlipped] = useState<boolean>(false);
    const [isSwapped, setIsSwapped] = useState<boolean>(false); // swap Side A and Side B
    const [isLoadingProgress, setIsLoadingProgress] = useState<boolean>(true);

    const [history, setHistory] = useState<{
        currentRoundVocabs: VocabularyResponse[];
        nextRoundVocabs: VocabularyResponse[];
        currentIndex: number;
    }[]>([]);

    // Progress persistence keyed by lesson ID
    const lessonId = lesson.id;

    const saveProgress = useCallback((
        currentRound: VocabularyResponse[],
        nextRound: VocabularyResponse[],
        index: number
    ) => {
        localStorage.setItem(
            `japan-go-flashcard-progress-${lessonId}`,
            JSON.stringify({
                currentRoundIds: currentRound.map((v) => v.id),
                nextRoundIds: nextRound.map((v) => v.id),
                currentIndex: index,
            })
        );
    }, [lessonId]);

    // Initialize progress from localStorage
    useEffect(() => {
        if (!open) return;
        const saved = localStorage.getItem(`japan-go-flashcard-progress-${lessonId}`);
        if (saved) {
            try {
                const { currentRoundIds, nextRoundIds, currentIndex: savedIndex } = JSON.parse(saved);
                
                const mapIdsToVocabs = (ids: number[]) => {
                    return ids
                        .map((id) => allVocabs.find((v) => v.id === id))
                        .filter((v): v is VocabularyResponse => !!v);
                };

                const current = mapIdsToVocabs(currentRoundIds);
                const next = mapIdsToVocabs(nextRoundIds);

                if (current.length > 0 || next.length > 0) {
                    setTimeout(() => {
                        setCurrentRoundVocabs(current);
                        setNextRoundVocabs(next);
                        setCurrentIndex(savedIndex);
                        setIsLoadingProgress(false);
                    }, 0);
                    return;
                }
            } catch (e) {
                console.error("Error loading saved flashcard progress:", e);
            }
        }
        
        // Fallback: load all vocabs into current round
        setTimeout(() => {
            setCurrentRoundVocabs(allVocabs);
            setNextRoundVocabs([]);
            setCurrentIndex(0);
            setIsLoadingProgress(false);
        }, 0);
    }, [open, lessonId, allVocabs]);

    // Handle card movement actions (Left/X and Right/Check)
    const handleNextCard = useCallback((markAsLearned: boolean) => {
        if (currentIndex >= currentRoundVocabs.length) return;
        
        // Push snapshot to history stack for Undo
        const snapshot = {
            currentRoundVocabs: [...currentRoundVocabs],
            nextRoundVocabs: [...nextRoundVocabs],
            currentIndex: currentIndex,
        };
        setHistory((prev) => [...prev, snapshot]);

        const activeVocab = currentRoundVocabs[currentIndex];
        const updatedNextRound = [...nextRoundVocabs];

        if (!markAsLearned) {
            if (!updatedNextRound.some((v) => v.id === activeVocab.id)) {
                updatedNextRound.push(activeVocab);
            }
        }

        const nextIndex = currentIndex + 1;

        if (nextIndex >= currentRoundVocabs.length) {
            // Current round complete
            if (updatedNextRound.length > 0) {
                setCurrentRoundVocabs(updatedNextRound);
                setNextRoundVocabs([]);
                setCurrentIndex(0);
                setIsFlipped(false);
                saveProgress(updatedNextRound, [], 0);
            } else {
                setCurrentIndex(nextIndex); // triggers completion state
                setIsFlipped(false);
                saveProgress([], [], nextIndex);
            }
        } else {
            setCurrentIndex(nextIndex);
            setNextRoundVocabs(updatedNextRound);
            setIsFlipped(false);
            saveProgress(currentRoundVocabs, updatedNextRound, nextIndex);
        }
    }, [currentIndex, currentRoundVocabs, nextRoundVocabs, saveProgress]);

    // Undo action (Curved back arrow)
    const handleUndo = useCallback(() => {
        if (history.length === 0) return;
        const prevHistory = [...history];
        const snapshot = prevHistory.pop()!;
        setHistory(prevHistory);
        setCurrentRoundVocabs(snapshot.currentRoundVocabs);
        setNextRoundVocabs(snapshot.nextRoundVocabs);
        setCurrentIndex(snapshot.currentIndex);
        setIsFlipped(false);
        saveProgress(snapshot.currentRoundVocabs, snapshot.nextRoundVocabs, snapshot.currentIndex);
    }, [history, saveProgress]);

    // Shuffle action (Trộn thẻ)
    const handleShuffle = useCallback(() => {
        if (currentRoundVocabs.length <= 1) return;
        const shuffled = [...currentRoundVocabs].sort(() => Math.random() - 0.5);
        setCurrentRoundVocabs(shuffled);
        setCurrentIndex(0);
        setHistory([]);
        setIsFlipped(false);
        saveProgress(shuffled, nextRoundVocabs, 0);
    }, [currentRoundVocabs, nextRoundVocabs, saveProgress]);

    // Reset/Restart learning session from the beginning
    const handleRestart = useCallback(() => {
        setCurrentRoundVocabs(allVocabs);
        setNextRoundVocabs([]);
        setCurrentIndex(0);
        setHistory([]);
        setIsFlipped(false);
        saveProgress(allVocabs, [], 0);
    }, [allVocabs, saveProgress]);

    // Keyboard Shortcuts listener
    useEffect(() => {
        if (!open || isLoadingProgress || allVocabs.length === 0) return;

        const handleKeyDown = (e: KeyboardEvent) => {
            const isCompleted = currentIndex >= currentRoundVocabs.length;

            if (e.key === " ") {
                e.preventDefault();
                if (!isCompleted) setIsFlipped((prev) => !prev);
            } else if (e.key === "ArrowDown") {
                e.preventDefault();
                if (!isCompleted) setIsFlipped((prev) => !prev);
            } else if (e.key === "ArrowLeft") {
                e.preventDefault();
                if (!isCompleted) handleNextCard(false); // Mark as review again
            } else if (e.key === "ArrowRight") {
                e.preventDefault();
                if (!isCompleted) handleNextCard(true);  // Mark as learned
            }
        };

        window.addEventListener("keydown", handleKeyDown);
        return () => window.removeEventListener("keydown", handleKeyDown);
    }, [open, isLoadingProgress, currentIndex, currentRoundVocabs, handleNextCard, allVocabs.length]);

    if (!open) return null;

    if (allVocabs.length === 0) {
        return (
            <Modal open={open} onClose={onClose}>
                <div className="bg-bgc-app border border-bdc-primary absolute top-1/2 left-1/2 flex -translate-x-1/2 -translate-y-1/2 flex-col items-center rounded-2xl p-8 max-w-md w-full shadow-2xl">
                    <SchoolOutlinedIcon className="text-neutral-400 w-16 h-16 mb-4" />
                    <p className="text-lg font-semibold text-center text-tc-primary">
                        Bài học này không có từ vựng nào để học thẻ ghi nhớ!
                    </p>
                    <button
                        onClick={onClose}
                        className="mt-6 px-5 py-2.5 bg-neutral-100 hover:bg-neutral-200 dark:bg-zinc-800 dark:hover:bg-zinc-700 text-sm font-semibold rounded-lg transition-colors border border-bdc-primary cursor-pointer"
                    >
                        Đóng
                    </button>
                </div>
            </Modal>
        );
    }

    const isCompleted = currentIndex >= currentRoundVocabs.length;
    const currentVocab = currentRoundVocabs[currentIndex];

    // Card Side content: Strokes + Japanese text
    const renderFrontStrokes = (vocab: VocabularyResponse) => (
        <div className="flex flex-col items-center justify-center gap-y-6 w-full h-full">
            {vocab.kanjiVgList && vocab.kanjiVgList.length > 0 ? (
                <div 
                    className="flex flex-nowrap gap-3 items-center justify-start sm:justify-center overflow-x-auto max-w-full pb-2 [&::-webkit-scrollbar]:hidden"
                    style={{ scrollbarWidth: "none", msOverflowStyle: "none" }}
                >
                    {vocab.kanjiVgList.map((svg, idx) => {
                        const len = vocab.kanjiVgList.length;
                        const size = len <= 1 ? 280 : len === 2 ? 240 : len === 3 ? 192 : 150;
                        return (
                            <KanjiVgAnimator key={idx} kanjiVg={svg} size={size} />
                        );
                    })}
                </div>
            ) : (
                <div className="text-neutral-400 dark:text-zinc-500 font-medium text-sm select-none italic pb-2">
                    Không có nét vẽ
                </div>
            )}
            <span className="text-5xl font-extrabold font-noto-sans-jp text-neutral-900 dark:text-neutral-50 tracking-wider">
                {vocab.japanese}
            </span>

            <div className="flex gap-x-3 items-center justify-center select-none" onClick={(e) => e.stopPropagation()}>
                <a
                    href={`https://mazii.net/vi-VN/search/word/ja/${encodeURIComponent(vocab.japanese)}`}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="flex items-center gap-x-1.5 px-3 py-1.5 rounded-lg border border-[#f57c00]/30 hover:border-[#f57c00]/60 bg-[#f57c00]/5 hover:bg-[#f57c00]/10 text-[#f57c00] text-xs font-bold transition-all shadow-sm cursor-pointer"
                >
                    <span>Tra từ Mazii</span>
                    <OpenInNewIcon sx={{ fontSize: "12px" }} />
                </a>
                <a
                    href={`https://www.weblio.jp/content/${encodeURIComponent(vocab.japanese)}`}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="flex items-center gap-x-1.5 px-3 py-1.5 rounded-lg border border-[#0288d1]/30 hover:border-[#0288d1]/60 bg-[#0288d1]/5 hover:bg-[#0288d1]/10 text-[#0288d1] text-xs font-bold transition-all shadow-sm cursor-pointer"
                >
                    <span>Tra từ Weblio</span>
                    <OpenInNewIcon sx={{ fontSize: "12px" }} />
                </a>
            </div>
        </div>
    );

    // Card Side content: Readings + Meanings + Note + Support vocab
    const renderBackDetails = (vocab: VocabularyResponse) => {
        const hasSupport = vocab.supportVocabularies && vocab.supportVocabularies.length > 0;
        return (
            <div className="flex flex-col items-center justify-center gap-y-4 w-full h-full max-w-2xl text-center overflow-y-auto px-2">
                {vocab.sinoVietnamese && (
                    <span className="text-xs font-bold tracking-widest uppercase text-blue-600 dark:text-blue-400 bg-blue-500/10 dark:bg-blue-950/40 px-3 py-1 rounded-full border border-blue-500/20 select-none">
                        {vocab.sinoVietnamese}
                    </span>
                )}
                
                <div className="flex flex-col gap-y-1">
                    <span className="text-[10px] font-bold text-neutral-400 dark:text-zinc-500 uppercase tracking-widest select-none">
                        CÁCH ĐỌC
                    </span>
                    <span className="font-noto-sans-jp text-3xl font-semibold text-neutral-800 dark:text-neutral-100">
                        {vocab.reading}
                    </span>
                </div>

                <div className="flex flex-col gap-y-1">
                    <span className="text-[10px] font-bold text-neutral-400 dark:text-zinc-500 uppercase tracking-widest select-none">
                        NGHĨA
                    </span>
                    <span className="text-2xl font-bold text-neutral-900 dark:text-neutral-50">
                        {vocab.meaning}
                    </span>
                </div>

                {vocab.note && vocab.note.trim().length > 0 && (
                    <div className="text-sm bg-red-500/5 text-red-700 dark:text-red-400 border border-red-500/10 rounded-xl p-3 flex items-start gap-x-2.5 w-full text-left">
                        <span className="font-bold text-red-500 dark:text-red-400 uppercase tracking-wider text-[10px] bg-red-500/10 px-1.5 py-0.5 rounded select-none mt-0.5">
                            Chú ý
                        </span>
                        <span className="flex-1 font-medium leading-normal">{vocab.note}</span>
                    </div>
                )}

                {hasSupport && (
                    <div className="border-t border-bdc-primary/45 w-full pt-3 flex flex-col gap-y-2 text-left items-start max-h-[160px] overflow-y-auto">
                        <span className="text-xs font-bold text-neutral-400 dark:text-zinc-500 uppercase tracking-widest select-none">
                            Từ bổ trợ
                        </span>
                        <div className="flex flex-col gap-y-1.5 w-full">
                            {vocab.supportVocabularies.map((support) => (
                                <div key={support.id} className="text-base font-medium text-neutral-700 dark:text-neutral-300 flex flex-wrap gap-x-2">
                                    <span className="font-bold text-neutral-900 dark:text-neutral-100">{support.japanese}</span>
                                    <span className="font-noto-sans-jp text-neutral-500 dark:text-neutral-400">({support.reading})</span>
                                    <span className="text-neutral-300 dark:text-zinc-700">|</span>
                                    <span className="text-neutral-600 dark:text-neutral-300">{support.meaning}</span>
                                </div>
                            ))}
                        </div>
                    </div>
                )}
            </div>
        );
    };

    // Responsive styles for card transforms
    const cardStyle: React.CSSProperties = {
        perspective: "1000px",
    };

    const cardInnerStyle: React.CSSProperties = {
        width: "100%",
        height: "100%",
        transition: "transform 0.6s cubic-bezier(0.4, 0, 0.2, 1)",
        transformStyle: "preserve-3d",
        transform: isFlipped ? "rotateY(180deg)" : "rotateY(0deg)",
        position: "relative",
    };

    const cardFaceFrontStyle: React.CSSProperties = {
        position: "absolute",
        width: "100%",
        height: "100%",
        backfaceVisibility: "hidden",
        WebkitBackfaceVisibility: "hidden",
    };

    const cardFaceBackStyle: React.CSSProperties = {
        position: "absolute",
        width: "100%",
        height: "100%",
        backfaceVisibility: "hidden",
        WebkitBackfaceVisibility: "hidden",
        transform: "rotateY(180deg)",
    };

    return (
        <Modal open={open} onClose={onClose} className="backdrop-blur-sm">
            <div className="bg-bgc-app/95 border border-bdc-primary dark:bg-zinc-950/95 absolute top-1/2 left-1/2 flex -translate-x-1/2 -translate-y-1/2 flex-col items-center rounded-2xl p-6 md:p-8 max-w-6xl w-[98%] h-[95vh] max-h-[900px] shadow-2xl overflow-hidden transition-all duration-200">
                {/* Modal Header */}
                <div className="flex items-center justify-between w-full pb-4 border-b border-bdc-primary/50">
                    <div className="flex items-center gap-x-2">
                        <SchoolOutlinedIcon className="text-neutral-500" fontSize="medium" />
                        <h2 className="text-xl font-bold tracking-tight text-tc-primary">
                            Học thẻ ghi nhớ
                        </h2>
                    </div>

                    <div className="flex items-center gap-x-2">
                        {!isCompleted && (
                            <TooltipCustom title="Đổi mặt thẻ trước / sau" placement="top">
                                <div>
                                    <IconButtonCustom onClick={() => setIsSwapped((prev) => !prev)}>
                                        <SwapHorizIcon fontSize="small" />
                                    </IconButtonCustom>
                                </div>
                            </TooltipCustom>
                        )}
                        <IconButtonCustom onClick={onClose}>
                            <CloseIcon fontSize="small" />
                        </IconButtonCustom>
                    </div>
                </div>

                {isLoadingProgress ? (
                    <div className="flex-1 flex items-center justify-center">
                        <span className="text-neutral-500 font-semibold animate-pulse text-sm">
                            Đang tải tiến trình...
                        </span>
                    </div>
                ) : isCompleted ? (
                    /* Completion Screen */
                    <div className="flex-1 flex flex-col items-center justify-center text-center p-6 gap-y-5 animate-fade-in w-full">
                        <div className="bg-green-500/10 border border-green-500/30 w-20 h-20 rounded-full flex items-center justify-center text-green-500 shadow-lg shadow-green-500/5">
                            <CheckIcon className="w-10 h-10" />
                        </div>
                        <h3 className="text-3xl font-extrabold text-neutral-900 dark:text-neutral-100 tracking-tight">
                            Chúc mừng bạn!
                        </h3>
                        <p className="text-lg font-medium text-neutral-600 dark:text-neutral-300 max-w-md">
                            Bạn đã học thuộc hoàn toàn tất cả các thẻ ghi nhớ của bài học này!
                        </p>
                        <button
                            onClick={handleRestart}
                            className="mt-4 px-6 py-3 bg-neutral-900 dark:bg-white text-white dark:text-black font-semibold rounded-xl hover:opacity-90 transition-opacity cursor-pointer shadow-lg active:scale-95"
                        >
                            Học lại từ đầu
                        </button>
                    </div>
                ) : (
                    /* Active Flashcard Interface */
                    <div className="flex-1 flex flex-col w-full items-center justify-between pt-4 gap-y-4">
                        {/* Tags Row for Learning Progress Tracking */}
                        <div className="w-full max-w-2xl flex items-center justify-between px-2">
                            {/* Đang học (Left) */}
                            <div className="flex items-center gap-x-2">
                                <span className="flex items-center justify-center min-w-[32px] h-7 rounded-full border border-orange-500/40 bg-orange-500/5 px-2.5 text-xs font-bold text-orange-500 select-none">
                                    {nextRoundVocabs.length}
                                </span>
                                <span className="text-sm font-bold text-orange-500 select-none">
                                    Đang học
                                </span>
                            </div>

                            {/* Đã biết (Right) */}
                            <div className="flex items-center gap-x-2">
                                <span className="text-sm font-bold text-green-500 select-none">
                                    Đã biết
                                </span>
                                <span className="flex items-center justify-center min-w-[32px] h-7 rounded-full border border-green-500/40 bg-green-500/5 px-2.5 text-xs font-bold text-green-500 select-none">
                                    {Math.max(0, currentIndex - nextRoundVocabs.length)}
                                </span>
                            </div>
                        </div>

                        {/* 3D Flashcard Container */}
                        <div 
                            style={cardStyle}
                            className="w-full max-w-5xl h-[560px]"
                        >
                            <div 
                                style={cardInnerStyle}
                                onClick={() => setIsFlipped((prev) => !prev)}
                            >
                                {/* Front Face */}
                                <div 
                                    style={cardFaceFrontStyle}
                                    className="bg-bgc-page dark:bg-zinc-900 border border-bdc-primary rounded-2xl p-6 flex flex-col items-center justify-center shadow-lg hover:shadow-xl hover:border-tc-highlight/45 transition-all duration-300"
                                >
                                    {isSwapped ? renderBackDetails(currentVocab) : renderFrontStrokes(currentVocab)}
                                </div>

                                {/* Back Face */}
                                <div 
                                    style={cardFaceBackStyle}
                                    className="bg-bgc-page dark:bg-zinc-900 border border-bdc-primary rounded-2xl p-6 flex flex-col items-center justify-center shadow-lg hover:shadow-xl hover:border-tc-highlight/45 transition-all duration-300"
                                >
                                    {isSwapped ? renderFrontStrokes(currentVocab) : renderBackDetails(currentVocab)}
                                </div>
                            </div>
                        </div>

                        {/* Progress Tracker */}
                        <div className="w-full max-w-md flex flex-col gap-y-2 items-center">
                            <span className="text-sm font-semibold text-neutral-500 select-none">
                                Tiến độ vòng này: {currentIndex + 1} / {currentRoundVocabs.length}
                            </span>
                            <div className="w-full h-2 bg-neutral-100 dark:bg-zinc-800 rounded-full overflow-hidden border border-bdc-primary">
                                <div 
                                    className="h-full bg-blue-500 rounded-full transition-all duration-300"
                                    style={{ width: `${((currentIndex + 1) / currentRoundVocabs.length) * 100}%` }}
                                />
                            </div>
                        </div>

                        {/* Bottom Actions Row (MacBook Style Buttons) */}
                        <div className="flex items-center justify-center gap-x-6 pb-2 select-none">
                            {/* X Button: Study again (Left) */}
                            <TooltipCustom title="Chưa thuộc (Mũi tên Trái)" placement="top">
                                <button
                                    onClick={() => handleNextCard(false)}
                                    className="w-18 h-12 bg-red-500/10 border border-red-500/20 hover:bg-red-500/20 text-red-600 dark:text-red-400 rounded-2xl flex items-center justify-center transition-all duration-150 cursor-pointer hover:scale-105 active:scale-95 shadow-sm hover:shadow-md"
                                >
                                    <CloseIcon className="w-6 h-6" />
                                </button>
                            </TooltipCustom>

                            {/* Check Button: Learned (Right) */}
                            <TooltipCustom title="Đã thuộc (Mũi tên Phải)" placement="top">
                                <button
                                    onClick={() => handleNextCard(true)}
                                    className="w-18 h-12 bg-green-500/10 border border-green-500/20 hover:bg-green-500/20 text-green-600 dark:text-green-400 rounded-2xl flex items-center justify-center transition-all duration-150 cursor-pointer hover:scale-105 active:scale-95 shadow-sm hover:shadow-md"
                                >
                                    <CheckIcon className="w-6 h-6" />
                                </button>
                            </TooltipCustom>

                            {/* Undo Button: Go back (Previous) */}
                            <TooltipCustom title="Quay lại thẻ trước" placement="top">
                                <div>
                                    <IconButtonCustom 
                                        onClick={handleUndo} 
                                        disabled={history.length === 0}
                                    >
                                        <UndoIcon fontSize="small" />
                                    </IconButtonCustom>
                                </div>
                            </TooltipCustom>

                            {/* Shuffle Button: Randomize deck */}
                            <TooltipCustom title="Trộn thẻ" placement="top">
                                <div>
                                    <IconButtonCustom onClick={handleShuffle} disabled={currentRoundVocabs.length <= 1}>
                                        <ShuffleIcon fontSize="small" />
                                    </IconButtonCustom>
                                </div>
                            </TooltipCustom>

                            {/* Reset Button: Restart deck study */}
                            <TooltipCustom title="Học lại từ đầu (Reset)" placement="top">
                                <div>
                                    <IconButtonCustom onClick={handleRestart}>
                                        <ReplayIcon fontSize="small" />
                                    </IconButtonCustom>
                                </div>
                            </TooltipCustom>
                        </div>
                    </div>
                )}
            </div>
        </Modal>
    );
};

export default FlashcardPopup;
