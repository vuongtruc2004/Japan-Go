"use client";
import { createContext, useContext } from "react";

interface IFolderDetailsWrapperProps {
    folder: FolderDetailsResponse;
}

const FolderDetailsContext = createContext<
    IFolderDetailsWrapperProps | undefined
>(undefined);

export const FolderDetailsWrapper = ({
    children,
    folder,
}: {
    children: React.ReactNode;
    folder: FolderDetailsResponse;
}) => {
    return (
        <FolderDetailsContext.Provider value={{ folder }}>
            {children}
        </FolderDetailsContext.Provider>
    );
};

export const useFolderDetails = () => {
    const context = useContext(FolderDetailsContext);
    if (!context) {
        throw new Error("wrapper error");
    }
    return context;
};
