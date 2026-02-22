"use client";
import React from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import WrapBox from "@/components/ui/wrap.box";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import IosShareOutlinedIcon from "@mui/icons-material/IosShareOutlined";
import { useTranslations } from "next-intl";
import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";
import ButtonCustom from "@/components/ui/mui-custom/button.custom";
import { useSearchParams } from "next/navigation";
import { Link } from "@/i18n/navigation";
import { slugifyText } from "@/utils/slugify.text";
import LessonDetailsMoreButton from "@/features/lesson/components/common/lesson.details.more.button";

const LessonHeader = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();
    const searchParams = useSearchParams();
    const folderId = searchParams.get("folderId");
    const folderName = searchParams.get("folderName");

    return (
        <WrapBox>
            <div className="flex items-center justify-between">
                {folderId && folderName ? (
                    <Link
                        href={{
                            pathname: "/your-library/folder/[slug]",
                            params: {
                                slug: slugifyText(folderName + "-" + folderId),
                            },
                        }}
                        className="hover:text-tc-highlight flex items-center gap-x-1.5 text-sm font-semibold hover:underline"
                    >
                        <span className="bg-bgc-page flex h-10 w-10 items-center justify-center rounded-md">
                            <FolderOutlinedIcon fontSize="small" />
                        </span>
                        {folderName}
                    </Link>
                ) : (
                    <h1 className="mt-3 text-3xl font-semibold">
                        {lesson.lessonName}
                    </h1>
                )}

                <div className="flex items-center gap-x-3">
                    <TooltipCustom title={t("Pages.lesson.saveToQuickAccess")}>
                        <ButtonCustom>
                            <BookmarkBorderOutlinedIcon fontSize="small" />
                            <p className="font-semibold">{t("Common.save")}</p>
                        </ButtonCustom>
                    </TooltipCustom>

                    <TooltipCustom title={t("Common.share")}>
                        <IconButtonCustom>
                            <IosShareOutlinedIcon fontSize="small" />
                        </IconButtonCustom>
                    </TooltipCustom>

                    <LessonDetailsMoreButton lesson={lesson} />
                </div>
            </div>
            {folderId && folderName && (
                <h1 className="mt-3 text-3xl font-semibold">
                    {lesson.lessonName}
                </h1>
            )}
        </WrapBox>
    );
};

export default LessonHeader;
