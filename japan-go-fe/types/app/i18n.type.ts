import { routing } from "@/i18n/routing";
import { Messages } from "next-intl";

export type AppLinkKeys = keyof Messages['Common']['links'];

export type AppLocales = (typeof routing.locales)[number];

export type AllRoutes = keyof typeof routing.pathnames;

export type RemoveDynamic<T> =
    T extends `${string}[${string}` ? never : T;

export type StaticRoutes = RemoveDynamic<AllRoutes>;

export type KanjiTabs = keyof Messages['Pages']['kanji']['explore']['levels'];