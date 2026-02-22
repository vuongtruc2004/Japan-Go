import { ILessonCreateLink, ILibraryTab } from "../types/your.library.ui.type";
import TranslateIcon from "@mui/icons-material/Translate";
import LibraryBooksOutlinedIcon from "@mui/icons-material/LibraryBooksOutlined";

export const LIBRARY_TABS: ILibraryTab[] = [
    {
        id: "tab-1",
        link: "/your-library",
        nameKey: "Common.class.title",
    },
    {
        id: "tab-2",
        link: "/your-library/folder",
        nameKey: "Common.folder.title",
    },
    {
        id: "tab-3",
        link: "/your-library/lesson",
        nameKey: "Common.lesson.title",
    },
];

export const COURSE_CREATE_LINK_ITEMS: ILessonCreateLink[] = [
    {
        id: "link-1",
        link: "/create-lesson/grammar",
        nameKey: "Pages.yourLibrary.lesson.grammarLesson",
        icon: <LibraryBooksOutlinedIcon />,
    },
    {
        id: "link-2",
        link: "/create-lesson/kanji",
        nameKey: "Pages.yourLibrary.lesson.kanjiLesson",
        icon: <TranslateIcon />,
    },
];
