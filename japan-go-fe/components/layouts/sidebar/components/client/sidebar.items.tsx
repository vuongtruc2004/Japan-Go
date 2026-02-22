"use client";
import { Box, Divider } from "@mui/material";
import { SIDEBAR_ITEMS } from "../../constants/sidebar.constants";
import { useSidebarCollapse } from "../../context/sidebar.collapse";
import LogoButton from "./logo.button";
import SidebarSingleItem from "./sidebar.single.item";
import PinAppCreateButton from "@/components/layouts/sidebar/components/client/pin.app.create.button";
import PinFolderList from "@/features/pin-folder/components/pin.folder.list";
import { FolderResponse } from "@/types/api/responses/common.response";

const SidebarItems = ({ pinFolders }: { pinFolders: FolderResponse[] }) => {
    const { isCollapse } = useSidebarCollapse();

    return (
        <Box
            sx={{
                position: "sticky",
                top: 0,
                left: 0,
                width: isCollapse ? "80px" : "240px",
                height: "100vh",
                paddingInline: "18px",
                transition: "all .2s ease",
                bgcolor: "var(--color-bgc-app)",
                borderRightWidth: "1px",
                borderRightStyle: "solid",
                borderRightColor: "var(--color-bdc-primary)",
            }}
        >
            <LogoButton />

            <div className="mt-5">
                <ul className="flex w-max flex-col gap-y-3">
                    {SIDEBAR_ITEMS.map((item) => {
                        return <SidebarSingleItem key={item.id} item={item} />;
                    })}
                </ul>

                <Divider sx={{ my: 3 }} />

                <div className="flex flex-col">
                    {pinFolders.length !== 0 && (
                        <>
                            <PinFolderList pinFolders={pinFolders} />

                            <Divider sx={{ my: 3 }} />
                        </>
                    )}

                    <PinAppCreateButton />
                </div>
            </div>
        </Box>
    );
};

export default SidebarItems;
