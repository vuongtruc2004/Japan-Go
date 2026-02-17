"use client";
import { usePathname } from "@/i18n/navigation";
import {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useEffect,
    useState,
} from "react";

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
    const defaultCollapseLinks = ["/lesson/kanji", "/lesson/grammar"];
    const initialState = defaultCollapseLinks.some((link) =>
        pathname.startsWith(link),
    );

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
        throw new Error(
            "useSidebarCollapse must be used within a SidebarCollapseProvider",
        );
    }
    return context;
};
