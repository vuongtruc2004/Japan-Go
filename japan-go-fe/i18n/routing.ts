import { defineRouting } from "next-intl/routing";

export const routing = defineRouting({
    // A list of all locales that are supported
    locales: ["vi"],

    // Used when no locale matches
    defaultLocale: "vi",
    // Hide all prefix in the url such as /en, /vi, /ja
    // localePrefix: 'never',

    pathnames: {
        "/home": {
            vi: "/trang-chu",
        },
        "/your-library": {
            vi: "/thu-vien-cua-ban",
        },
        "/your-library/folder": {
            vi: "/thu-vien-cua-ban/thu-muc",
        },
        "/your-library/folder/[slug]": {
            vi: "/thu-vien-cua-ban/thu-muc/[slug]",
        },
        "/your-library/course": {
            vi: "/thu-vien-cua-ban/hoc-phan",
        },
        "/kanji": {
            vi: "/chu-han",
        },
        "/settings": {
            vi: "/cai-dat",
        },
        "/achievement": {
            vi: "thanh-tuu",
        },
        "/register": {},
        "/login": {},
    },
});
