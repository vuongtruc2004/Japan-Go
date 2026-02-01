import { useSidebarCollapse } from "@/libs/wrapper/context/sidebar.collapse.wrapper";
import { Divider } from "@mui/material";
import { useTranslations } from "next-intl";
import { ISidebarGroup } from "../models/sidebar.type";
import SidebarSingleItem from "./sidebar.single.item";

const SidebarSingleGroup = ({ group }: { group: ISidebarGroup }) => {
    const { isCollapse } = useSidebarCollapse();
    const t = useTranslations("AppSidebar.groupsTitle");

    return (
        <>
            {!isCollapse && (
                <h1 className="text-tc-primary ml-3 text-sm font-semibold uppercase">
                    {t(`${group.groupKeyTranslation}`)}
                </h1>
            )}
            {group.items.map((item) => {
                return <SidebarSingleItem key={item.id} item={item} />;
            })}
            {!group.isLastGroup && (
                <Divider
                    sx={{
                        borderColor: "var(--color-bdc-primary)",
                        borderWidth: "1px",
                        marginBlock: "4px",
                    }}
                />
            )}
        </>
    );
};

export default SidebarSingleGroup;
