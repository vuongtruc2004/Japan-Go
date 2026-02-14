"use client";
import { Button, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import CloseOutlinedIcon from "@mui/icons-material/CloseOutlined";
import {
    ButtonHTMLAttributes,
    cloneElement,
    ReactElement,
    useEffect,
    useState,
} from "react";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import SingleCourseToAdd from "@/features/your-library/components/course/single.course.to.add";
import Empty from "@/components/ui/empty";
import CourseCreateButton from "@/features/your-library/components/course/course.create.button";

const FolderAddCourseButton = ({
    buttonElement,
}: {
    buttonElement: ReactElement<ButtonHTMLAttributes<HTMLButtonElement>>;
}) => {
    const t = useTranslations();
    const [lessons, setLessons] = useState<LessonResponse[]>([]);
    const [open, setOpen] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
        // const fetch = async () => {
        //     const page = await getAllLessons();
        //     setLessons(page.content);
        // };
        // fetch();
    }, []);

    return (
        <>
            {cloneElement(buttonElement, { onClick: () => setOpen(true) })}

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 flex w-150 -translate-x-1/2 -translate-y-1/2 flex-col gap-y-3 rounded-md p-5">
                    <h1 className="font-semibold">
                        {t("Pages.yourLibrary.folder.addCourse")}
                    </h1>

                    <div className="flex items-center justify-between">
                        <p className="text-sm font-semibold">
                            {t("Common.course.title")}
                        </p>
                        <CourseCreateButton />
                    </div>

                    <TooltipCustom
                        title={t("Common.closeWindow")}
                        color={"--color-tc-error"}
                        placement="right"
                    >
                        <button
                            className="hover:bg-hbgc-app hover:text-tc-error absolute top-3 right-3 flex h-9 w-9 cursor-pointer items-center justify-center rounded-full transition-all duration-150"
                            onClick={handleClose}
                        >
                            <CloseOutlinedIcon fontSize="small" />
                        </button>
                    </TooltipCustom>

                    <div>
                        {lessons.length === 0 ? (
                            <Empty
                                text={t("Pages.yourLibrary.course.noCourses")}
                            />
                        ) : (
                            lessons.map((lesson) => {
                                return (
                                    <SingleCourseToAdd
                                        lesson={lesson}
                                        key={lesson.id}
                                    />
                                );
                            })
                        )}
                    </div>
                    <div className="flex justify-end">
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleClose}
                        >
                            {t("Common.complete")}
                        </Button>
                    </div>
                </div>
            </Modal>
        </>
    );
};

export default FolderAddCourseButton;
