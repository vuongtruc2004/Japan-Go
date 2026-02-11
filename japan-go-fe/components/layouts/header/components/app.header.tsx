"use client";
import AppCreateButton from "./app.create.button";
import AppSearchBox from "./app.search.box";
import NotificationButton from "./notification.button";
import SidebarActionButton from "./sidebar.action.button";
import UserAvatar from "./user.avatar";

const AppHeader = () => {
    return (
        <div className="border-b-bdc-primary bg-bgc-app sticky top-0 left-0 z-10 flex items-center justify-between border-b px-3 py-3.5">
            <div className="flex items-center gap-x-3">
                <SidebarActionButton />
                <AppSearchBox />
            </div>

            <div className="flex items-center">
                <div className="flex items-center gap-x-1">
                    <NotificationButton />
                    <AppCreateButton />
                </div>
                <UserAvatar />
            </div>
        </div>
    );
};

export default AppHeader;

