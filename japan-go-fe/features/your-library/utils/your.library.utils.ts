import { LIBRARY_TABS } from "@/features/your-library/constants/your.library.constants";
import { FolderResponse } from "@/types/api/responses/common.response";

export const getActiveLibraryTab = (pathname: string): number => {
    for (let i = LIBRARY_TABS.length - 1; i >= 0; i--) {
        if (pathname.startsWith(LIBRARY_TABS[i].link)) {
            return i;
        }
    }
    return -1;
};

export const folderHasLesson = (
    folder: FolderResponse,
    lessonId: number,
): boolean => {
    return folder.lessons.some((lesson) => lesson.id === lessonId);
};
