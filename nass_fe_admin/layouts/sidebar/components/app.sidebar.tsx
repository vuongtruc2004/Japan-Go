"use client";
import { useSidebarCollapse } from "@/libs/wrapper/context/sidebar.collapse.wrapper";
import { Box } from "@mui/material";
import LogoButton from "../features/logo.button";
import { SIDEBAR_GROUPS } from "../models/sidebar.groups";
import SidebarSingleGroup from "./sidebar.single.group";

const AppSidebar = () => {
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
            <ul className="mt-6 flex w-max flex-col gap-y-3">
                {SIDEBAR_GROUPS.map((group) => {
                    return <SidebarSingleGroup key={group.id} group={group} />;
                })}
            </ul>
        </Box>
    );
};

export default AppSidebar;
