export const getKanjiPageMoveButtonClassname = (
    activeIndex: number,
    index: number,
): string => {
    if (activeIndex === index)
        return "border-green-500/20 bg-green-500/10 py-2 text-green-700 dark:text-green-500";
    return "hover:bg-hbgc-highlight transition-all duration-150 border-bdc-primary";
};
