"use client";
import { Button, Modal } from "@mui/material";
import { useTranslations } from "next-intl";
import CloseOutlinedIcon from "@mui/icons-material/CloseOutlined";
import React, {
    ButtonHTMLAttributes,
    cloneElement,
    ReactElement,
    useEffect,
    useState,
} from "react";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import Empty from "@/components/ui/empty";
import LessonCreateButton from "@/components/domain/create-lesson/lesson.create.button";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";
import SingleLessonToAdd from "@/features/your-library/components/lesson/single.lesson.to.add";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";

const FolderAddLessonButton = ({
    buttonElement,
}: {
    buttonElement: ReactElement<ButtonHTMLAttributes<HTMLButtonElement>>;
}) => {
    const t = useTranslations();
    const { folder } = useFolderDetails();

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
                        {t("Pages.yourLibrary.folder.addLesson")}
                    </h1>

                    <div className="flex items-center justify-between">
                        <p className="text-sm font-semibold">
                            {t("Common.lesson.title")}
                        </p>
                        <LessonCreateButton
                            folder={folder}
                            buttonElement={
                                <Button variant={"text"} color={"primary"}>
                                    <AddOutlinedIcon fontSize="small" />
                                    <p className="font-semibold">
                                        {t("Common.lesson.create")}
                                    </p>
                                </Button>
                            }
                        />
                    </div>

                    <TooltipCustom
                        title={t("Common.closeWindow")}
                        color={"--color-tc-error"}
                        placement="right"
                    >
                        <button
                            className="hover:bg-hbgc-app hover:text-tc-error absolute top-3 right-3 flex h-10 w-10 cursor-pointer items-center justify-center rounded-full transition-all duration-150"
                            onClick={handleClose}
                        >
                            <CloseOutlinedIcon fontSize="small" />
                        </button>
                    </TooltipCustom>

                    <div>
                        {lessons.length === 0 ? (
                            <Empty
                                text={t("Pages.yourLibrary.lesson.noLessons")}
                            />
                        ) : (
                            lessons.map((lesson) => {
                                return (
                                    <SingleLessonToAdd
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

export default FolderAddLessonButton;
