import React, { useState } from "react";
import { useTranslations } from "next-intl";
import AddIcon from "@mui/icons-material/Add";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useSidebarCollapse } from "@/components/layouts/sidebar/context/sidebar.collapse";
import AppCreatePopover from "@/components/ui/popovers/app.create.popover";

const QuickAccess = () => {
    const { isCollapse } = useSidebarCollapse();
    const t = useTranslations("Common");

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    return (
        <div>
            <TooltipCustom
                title={isCollapse ? t("createNew") : ""}
                placement="right"
                arrow
            >
                <button
                    onClick={(event) => setAnchorEl(event.currentTarget)}
                    className={`${isCollapse ? "w-10" : "w-50"} text-tc-muted hover:text-tc-highlight hover:bg-hbgc-app flex h-10 cursor-pointer items-center overflow-hidden rounded-md pl-2 transition-all duration-150`}
                >
                    <span className="flex h-10 items-center justify-center">
                        <AddIcon />
                    </span>
                    <p
                        className={`text-sm font-semibold whitespace-nowrap ${isCollapse && "hidden"} ml-4`}
                    >
                        {t("createNew")}
                    </p>
                </button>
            </TooltipCustom>

            <AppCreatePopover
                hasHeader={false}
                anchorEl={anchorEl}
                setAnchorEl={setAnchorEl}
                popoverAnchorOrigin={{
                    vertical: "center",
                    horizontal: "right",
                }}
                popoverTransformOrigin={{
                    vertical: "center",
                    horizontal: "left",
                }}
                lessonCreateButtonAnchorOrigin={{
                    vertical: "center",
                    horizontal: "right",
                }}
                lessonCreateButtonTransformOrigin={{
                    vertical: "center",
                    horizontal: "left",
                }}
            />
        </div>
    );
};

export default QuickAccess;
