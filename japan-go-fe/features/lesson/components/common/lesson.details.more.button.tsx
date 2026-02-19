import React, { useState } from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { Divider, Popover } from "@mui/material";
import { useTranslations } from "next-intl";
import { LESSON_DETAILS_MORE_BUTTON_ITEMS } from "@/features/lesson/constants/lesson.constants";
import LessonDeleteButton from "@/features/lesson/components/common/lesson.delete.button";

const LessonDetailsMoreButton = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    return (
        <>
            <TooltipCustom title={t("Common.viewMore")}>
                <IconButtonCustom
                    onClick={(event) => setAnchorEl(event.currentTarget)}
                >
                    <MoreHorizOutlinedIcon />
                </IconButtonCustom>
            </TooltipCustom>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={() => setAnchorEl(null)}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <div className="flex flex-col">
                    <h1 className="text-tc-muted px-3.5 py-3 text-sm font-semibold">
                        {t("Pages.lesson.lessonAction")}
                    </h1>

                    <Divider />

                    {LESSON_DETAILS_MORE_BUTTON_ITEMS.map((item) => {
                        return (
                            <button
                                key={item.id}
                                className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150"
                            >
                                {item.icon}
                                <p className="text-sm font-semibold whitespace-nowrap">
                                    {t(
                                        `Pages.lesson.lessonDetailsMoreButtons.${item.textKey}`,
                                    )}
                                </p>
                            </button>
                        );
                    })}

                    <Divider />

                    <LessonDeleteButton lesson={lesson} />
                </div>
            </Popover>
        </>
    );
};

export default LessonDetailsMoreButton;
