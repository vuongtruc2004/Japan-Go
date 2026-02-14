import FolderCreateButton from "@/components/domain/folder/folder.create.button";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";
import { Button, Divider, Popover } from "@mui/material";
import { useTranslations } from "next-intl";
import { useState } from "react";
import ClassCreateButton from "./class.create.button";
import CourseCreateButton from "./course.create.button";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";

const AppCreateButton = () => {
    const t = useTranslations();
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <>
            <TooltipCustom arrow title={t("Common.create")}>
                <Button
                    onClick={handleClick}
                    variant="text"
                    color="success"
                    sx={{
                        width: "36px",
                        minWidth: "36px",
                        height: "36px",
                        borderRadius: "50%",
                    }}
                >
                    <AddCircleOutlineOutlinedIcon />
                </Button>
            </TooltipCustom>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <div>
                    <p className="text-tc-muted px-3.5 py-3 text-sm font-semibold">
                        {t("AppHeader.appCreate")}
                    </p>
                    <Divider />
                    <ClassCreateButton />
                    <FolderCreateButton
                        buttonElement={
                            <button className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150">
                                <FolderOutlinedIcon />
                                <p className="text-sm font-semibold whitespace-nowrap">
                                    {t("Common.folder.title")}
                                </p>
                            </button>
                        }
                    />
                    <CourseCreateButton />
                </div>
            </Popover>
        </>
    );
};

export default AppCreateButton;
