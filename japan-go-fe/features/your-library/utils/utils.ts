import { LIBRARY_TABS } from "@/features/your-library/constants/constants";

export const getActiveLibraryTab = (pathname: string): number => {
    for (let i = LIBRARY_TABS.length - 1; i >= 0; i--) {
        if (pathname.startsWith(LIBRARY_TABS[i].link)) {
            return i;
        }
    }
    return -1;
};
