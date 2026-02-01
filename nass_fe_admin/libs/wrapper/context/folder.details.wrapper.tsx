"use client";
import {
    createContext,
    Dispatch,
    SetStateAction,
    useContext,
    useState,
} from "react";

interface IFolderDetailsWrapperProps {
    openCreateModal: boolean;
    setOpenCreateModal: Dispatch<SetStateAction<boolean>>;
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
    const [openCreateModal, setOpenCreateModal] = useState(false);

    return (
        <FolderDetailsContext.Provider
            value={{ folder, openCreateModal, setOpenCreateModal }}
        >
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
