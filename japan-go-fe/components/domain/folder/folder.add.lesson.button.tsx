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
    useTransition,
} from "react";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import Empty from "@/components/ui/empty";
import LessonCreateButton from "@/components/domain/create-lesson/lesson.create.button";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";
import { getAllLessons } from "@/services/lesson.service";
import SingleLessonToAdd from "@/features/your-library/components/lesson/single.lesson.to.add";

const FolderAddLessonButton = ({
    buttonElement,
}: {
    buttonElement: ReactElement<ButtonHTMLAttributes<HTMLButtonElement>>;
}) => {
    const t = useTranslations();
    const { folder } = useFolderDetails();

    const [lessons, setLessons] = useState<LessonResponse[]>([]);
    const [open, setOpen] = useState(false);
    const [isPending, startTransition] = useTransition();

    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
        startTransition(() => {
            getAllLessons().then((response) => {
                setLessons(response.data.content);
            });
        });
    }, []);

    return (
        <>
            {cloneElement(buttonElement, { onClick: () => setOpen(true) })}

            <Modal open={open}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 flex w-200 -translate-x-1/2 -translate-y-1/2 flex-col gap-y-3 rounded-md p-5">
                    <h1 className="pl-2 font-semibold">
                        {t("Pages.yourLibrary.folder.addLesson")}
                    </h1>

                    <div className="flex items-center justify-between px-2">
                        <p className="text-sm font-semibold">
                            {t("Common.lesson.title")}
                        </p>
                        <LessonCreateButton
                            folder={folder}
                            onParentClose={handleClose}
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

                    <div className="flex max-h-65 flex-col gap-y-3 overflow-y-auto">
                        {isPending ? (
                            <p>Loading...</p>
                        ) : lessons.length === 0 ? (
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
