import { StaticRoutes } from "@/types/app/i18n.type";
import React from "react";

export interface ILibraryTab {
    readonly id: string;
    link: StaticRoutes;
    nameKey:
        | "Common.class.title"
        | "Common.folder.title"
        | "Common.lesson.title";
}

export interface ILessonCreateLink {
    readonly id: string;
    link: StaticRoutes;
    icon: React.ReactNode;
    nameKey:
        | "Pages.yourLibrary.lesson.grammarLesson"
        | "Pages.yourLibrary.lesson.kanjiLesson";
}
