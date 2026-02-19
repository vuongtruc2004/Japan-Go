import { DecoratorClassName } from "@/types/enums/share.enum";

export const getKanjiPageMoveButtonClassname = (
    activeIndex: number,
    index: number,
): string => {
    if (activeIndex === index) return DecoratorClassName.GREEN;
    return DecoratorClassName.PRIMARY;
};
