import { ICourseCreateLink, ILibraryTab } from "../types/ui.type";
import TranslateIcon from "@mui/icons-material/Translate";
import AbcIcon from "@mui/icons-material/Abc";

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
        link: "/your-library/course",
        nameKey: "Common.course.title",
    },
];

export const COURSE_CREATE_LINK_ITEMS: ICourseCreateLink[] = [
    {
        id: "link-1",
        link: "/create-course/grammar",
        nameKey: "Pages.yourLibrary.course.grammarCourse",
        icon: <AbcIcon />,
    },
    {
        id: "link-2",
        link: "/create-course/kanji",
        nameKey: "Pages.yourLibrary.course.kanjiCourse",
        icon: <TranslateIcon />,
    },
];
