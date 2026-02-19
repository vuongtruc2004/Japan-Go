import React from "react";
import { Divider, Popover, PopoverOrigin } from "@mui/material";
import ClassCreateButton from "@/components/layouts/header/components/class.create.button";
import FolderCreateButton from "@/components/domain/folder/folder.create.button";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import LessonCreateButton from "@/components/domain/create-lesson/lesson.create.button";
import StyleOutlinedIcon from "@mui/icons-material/StyleOutlined";
import { useTranslations } from "next-intl";

const AppCreatePopover = ({
    hasHeader = true,
    popoverAnchorOrigin,
    popoverTransformOrigin,
    lessonCreateButtonAnchorOrigin,
    lessonCreateButtonTransformOrigin,
    anchorEl,
    setAnchorEl,
}: {
    hasHeader?: boolean;
    popoverAnchorOrigin?: PopoverOrigin;
    popoverTransformOrigin?: PopoverOrigin;
    lessonCreateButtonAnchorOrigin?: PopoverOrigin;
    lessonCreateButtonTransformOrigin?: PopoverOrigin;
    anchorEl: HTMLButtonElement | null;
    setAnchorEl: React.Dispatch<React.SetStateAction<HTMLButtonElement | null>>;
}) => {
    const t = useTranslations();

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={
                    popoverAnchorOrigin || {
                        vertical: "bottom",
                        horizontal: "right",
                    }
                }
                transformOrigin={
                    popoverTransformOrigin || {
                        vertical: "top",
                        horizontal: "right",
                    }
                }
            >
                <div>
                    {hasHeader && (
                        <>
                            <h1 className="text-tc-muted px-3.5 py-3 text-sm font-semibold">
                                {t("AppHeader.appCreate")}
                            </h1>

                            <Divider />
                        </>
                    )}

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

                    <LessonCreateButton
                        anchorOrigin={
                            lessonCreateButtonAnchorOrigin || {
                                vertical: "center",
                                horizontal: "left",
                            }
                        }
                        transformOrigin={
                            lessonCreateButtonTransformOrigin || {
                                vertical: "center",
                                horizontal: "right",
                            }
                        }
                        buttonElement={
                            <button className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150">
                                <StyleOutlinedIcon />
                                <p className="text-sm font-semibold whitespace-nowrap">
                                    {t("Common.lesson.title")}
                                </p>
                            </button>
                        }
                    />
                </div>
            </Popover>
        </div>
    );
};

export default AppCreatePopover;
