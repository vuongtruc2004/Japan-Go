"use client";
import React, { createContext, useContext } from "react";
import { FolderResponse } from "@/types/api/responses/common.response";

interface IFolderDetailsProviderProps {
    folder: FolderResponse;
}

const FolderDetailsContext = createContext<
    IFolderDetailsProviderProps | undefined
>(undefined);

export const FolderDetailsProvider = ({
    children,
    folder,
}: {
    children: React.ReactNode;
    folder: FolderResponse;
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
        throw new Error("provider error");
    }
    return context;
};
