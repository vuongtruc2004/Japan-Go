import { routing } from "@/i18n/routing";
import { Messages } from "next-intl";

export type AppNameLinkKeys = keyof Messages["Common"]["links"];

export type AppLocales = (typeof routing.locales)[number];

export type AllRoutes = keyof typeof routing.pathnames;

export type RemoveDynamic<T> = T extends `${string}[${string}` ? never : T;

export type StaticRoutes = RemoveDynamic<AllRoutes>;

export type KanjiTabs = keyof Messages["Pages"]["kanji"]["explore"]["levels"];

export type LessonDetailsMoreButtonTextKey =
    keyof Messages["Pages"]["lesson"]["lessonDetailsMoreButtons"];

export type ShowVocabularyLabelKey =
    keyof Messages["Pages"]["lesson"]["kanji"]["showVocabularyLabels"];

export type HideVocabularyLabelKey =
    keyof Messages["Pages"]["lesson"]["kanji"]["hideVocabularyLabels"];
