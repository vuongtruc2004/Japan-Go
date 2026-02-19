import React, { useState } from "react";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";
import { useTranslations } from "next-intl";
import { Popover } from "@mui/material";
import WarningAmberIcon from "@mui/icons-material/WarningAmber";
import { deleteLesson } from "@/services/lesson.service";
import { useRouter } from "@/i18n/navigation";
import { useSearchParams } from "next/navigation";
import { slugifyText } from "@/utils/slugify.text";
import BasicButton from "@/components/ui/buttons/basic.button";

const LessonDeleteButton = ({ lesson }: { lesson: LessonResponse }) => {
    const t = useTranslations();
    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const searchParams = useSearchParams();
    const folderId = searchParams.get("folderId");
    const folderName = searchParams.get("folderName");
    const { push } = useRouter();

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleDeleteLesson = async () => {
        await deleteLesson(lesson.id);
        push({
            pathname: "/your-library/folder/[slug]",
            params: {
                slug: slugifyText(folderName + "-" + folderId),
            },
        });
    };

    return (
        <>
            <BasicButton
                icon={<DeleteOutlineOutlinedIcon />}
                text={t("Pages.lesson.deleteLesson")}
                textColor="text-tc-error"
                onClick={(event) => setAnchorEl(event.currentTarget)}
            />

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "center",
                    horizontal: "left",
                }}
                transformOrigin={{
                    vertical: "center",
                    horizontal: "right",
                }}
            >
                <div className="w-62.5 px-4 py-2">
                    <div className="mb-3 flex items-start gap-x-3">
                        <WarningAmberIcon color="warning" />
                        <div className="text-sm">
                            <p className="font-semibold">
                                {t("Pages.lesson.deleteLesson")}:{" "}
                                {lesson.lessonName}
                            </p>

                            <p>{t("Pages.lesson.areYouSureToDeleteLesson")}</p>
                        </div>
                    </div>
                    <div className="flex items-center justify-end gap-x-1.5">
                        <button
                            onClick={handleClose}
                            className="border-bdc-primary cursor-pointer rounded-sm border px-1.75 py-0.5 text-sm font-semibold"
                        >
                            {t("Common.cancel")}
                        </button>

                        <button
                            onClick={handleDeleteLesson}
                            className="border-bdc-primary bg-bgc-error text-tc-pure cursor-pointer rounded-sm border px-1.75 py-0.5 text-sm font-semibold"
                        >
                            {t("Common.delete")}
                        </button>
                    </div>
                </div>
            </Popover>
        </>
    );
};

export default LessonDeleteButton;
