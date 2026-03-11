import { DecoratorClassName } from "@/types/enums/share.enum";
import { IGrammarJlptLevel } from "@/features/create-lesson/types/create.lesson.ui.type";

export const GRAMMAR_JLPT_LEVELS: IGrammarJlptLevel[] = [
    {
        id: "tab-1",
        tabKeyTranslation: "n5",
        jlptLevel: 5,
        colorStyle: DecoratorClassName.GREEN,
    },
    {
        id: "tab-2",
        tabKeyTranslation: "n4",
        jlptLevel: 4,
        colorStyle: DecoratorClassName.BLUE,
    },
    {
        id: "tab-3",
        tabKeyTranslation: "n3",
        jlptLevel: 3,
        colorStyle: DecoratorClassName.YELLOW,
    },
    {
        id: "tab-4",
        tabKeyTranslation: "n2",
        jlptLevel: 2,
        colorStyle: DecoratorClassName.ORANGE,
    },
    {
        id: "tab-5",
        tabKeyTranslation: "n1",
        jlptLevel: 1,
        colorStyle: DecoratorClassName.RED,
    },
];
