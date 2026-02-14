import { StaticRoutes } from "@/types/app/i18n.type";

export interface ILibraryTab {
    readonly id: string;
    link: StaticRoutes;
    nameKey:
        | "Common.class.title"
        | "Common.folder.title"
        | "Common.course.title";
}
