"use client";
import { usePathname } from "@/i18n/navigation";
import React, {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useEffect,
    useState,
} from "react";
import { AllRoutes } from "@/types/app/i18n.type";

interface ISidebarCollapseProviderProps {
    isCollapse: boolean;
    setIsCollapse: Dispatch<SetStateAction<boolean>>;
}

const SidebarCollapseContext = createContext<
    ISidebarCollapseProviderProps | undefined
>(undefined);

export const SidebarCollapseProvider = ({
    children,
}: {
    children: React.ReactNode;
}) => {
    const pathname = usePathname();
    const collapseLinks: AllRoutes[] = [
        "/lesson/kanji/[slug]",
        "/lesson/grammar/[slug]",
    ];

    const initialState = collapseLinks.some((link) => pathname === link);

    const [isCollapse, setIsCollapse] = useState<boolean>(initialState);

    useEffect(() => {
        setIsCollapse(initialState);
    }, [initialState]);

    return (
        <SidebarCollapseContext.Provider value={{ isCollapse, setIsCollapse }}>
            {children}
        </SidebarCollapseContext.Provider>
    );
};

export const useSidebarCollapse = () => {
    const context = useContext(SidebarCollapseContext);
    if (!context) {
        throw new Error("provider error");
    }
    return context;
};
