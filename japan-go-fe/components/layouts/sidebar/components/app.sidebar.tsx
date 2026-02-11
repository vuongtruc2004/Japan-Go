"use client";
import { Box } from "@mui/material";
import { SIDEBAR_ITEMS } from "../constants/constants";
import { useSidebarCollapse } from "../context/sidebar.collapse";
import LogoButton from "./logo.button";
import SidebarSingleItem from "./sidebar.single.item";

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
                {SIDEBAR_ITEMS.map((item) => {
                    return <SidebarSingleItem key={item.id} item={item} />;
                })}
            </ul>
        </Box>
    );
};

export default AppSidebar;

