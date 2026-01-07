import AppHeader from "@/layouts/header/components/app.header";
import AppSidebar from "@/layouts/sidebar/components/app.sidebar";
import { SidebarCollapseWrapper } from "@/libs/wrapper/context/sidebar-collapse/sidebar.collapse";
import { ReactNode } from "react";

const PublicLayout = ({ children }: Readonly<{ children: ReactNode }>) => {
    return (
        <SidebarCollapseWrapper initialState={false}>
            <div className="relative flex">
                <AppSidebar />

                <div className="flex flex-1 flex-col">
                    <AppHeader />
                    <div className="bg-bgc-page w-full flex-1 overflow-x-hidden p-5">
                        {children}
                    </div>
                </div>
            </div>
        </SidebarCollapseWrapper>
    );
};

export default PublicLayout;
