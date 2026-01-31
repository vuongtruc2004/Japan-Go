import { defineRouting } from 'next-intl/routing';

export const routing = defineRouting({
    // A list of all locales that are supported
    locales: ['en', 'vi', 'ja'],

    // Used when no locale matches
    defaultLocale: 'vi',
    // Hide all prefix in the url such as /en, /vi, /ja
    localePrefix: 'never',

    pathnames: {
        '/home': {
            vi: '/trang-chu',
            en: '/home',
            ja: '/ホーム'
        },
        '/your-folder': {
            vi: '/thu-muc-cua-ban',
            en: '/your-folder',
            ja: '/あなたのフォルダ'
        },
        "/your-folder/[slug]": {
            vi: '/thu-muc-cua-ban/[slug]',
            en: '/your-folder/[slug]',
            ja: '/あなたのフォルダ/[slug]'
        },
        "/kanji": {
            vi: '/chu-han',
            en: '/kanji',
            ja: '/漢字'
        },
        "/settings": {
            vi: '/cai-dat',
            en: '/settings',
            ja: '/設定'
        },
        "/register": {

        },
        "/login": {

        }
    }
});