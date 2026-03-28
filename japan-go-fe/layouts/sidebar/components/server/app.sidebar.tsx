import React from "react";
import { getAllPinFolders } from "@/services/folder.service";
import SidebarItems from "@/layouts/sidebar/components/client/sidebar.items";

const AppSidebar = async () => {
    const pinFolders = await getAllPinFolders();
    return <SidebarItems pinFolders={pinFolders} />;
};

export default AppSidebar;
