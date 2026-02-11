import AppHeader from "@/components/layouts/header/components/app.header";
import AppSidebar from "@/components/layouts/sidebar/components/app.sidebar";
import { SidebarCollapseProvider } from "@/components/layouts/sidebar/context/sidebar.collapse";
import { ReactNode } from "react";

const SidebarOpenLayout = ({ children }: Readonly<{ children: ReactNode }>) => {
    return (
        <SidebarCollapseProvider>
            <div className="relative flex">
                <AppSidebar />

                <div className="flex min-w-0 flex-1 flex-col">
                    <AppHeader />
                    <div className="bg-bgc-page w-full flex-1 p-5">
                        {children}
                    </div>
                </div>
            </div>
        </SidebarCollapseProvider>
    );
};

export default SidebarOpenLayout;
