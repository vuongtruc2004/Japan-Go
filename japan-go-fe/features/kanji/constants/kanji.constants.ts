import { IGroupKanjiTab } from "../types/kanji.ui.type";
import { DecoratorClassName } from "@/types/enums/share.enum";

export const GROUP_KANJI_TAB: IGroupKanjiTab[] = [
    {
        id: "group-1",
        kanjiTabs: [
            {
                id: "tab-1",
                tabKeyTranslation: "n5",
                colorStyle: DecoratorClassName.GREEN,
                level: 5,
            },
            {
                id: "tab-2",
                tabKeyTranslation: "n4",
                colorStyle: DecoratorClassName.BLUE,
                level: 4,
            },
            {
                id: "tab-3",
                tabKeyTranslation: "n3",
                colorStyle: DecoratorClassName.YELLOW,
                level: 3,
            },
        ],
    },
    {
        id: "group-2",
        kanjiTabs: [
            {
                id: "tab-4",
                tabKeyTranslation: "n2",
                colorStyle: DecoratorClassName.ORANGE,
                level: 2,
            },
            {
                id: "tab-5",
                tabKeyTranslation: "n1",
                colorStyle: DecoratorClassName.RED,
                level: 1,
            },
        ],
    },
];
