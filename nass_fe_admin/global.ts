import { Messages } from 'next-intl';
import { routing } from './i18n/routing';
import messages from './messages/en.json';

declare module 'next-intl' {
    interface AppConfig {
        Messages: typeof messages;
    }
}

export type SidebarLinks = keyof Messages['AppSidebar']['links'];
export type AppLocales = (typeof routing.locales)[number];
export type AppLinks = keyof typeof routing.pathnames;
export type KanjiTabs = keyof Messages['Pages']['kanji']['explore']['levels'];