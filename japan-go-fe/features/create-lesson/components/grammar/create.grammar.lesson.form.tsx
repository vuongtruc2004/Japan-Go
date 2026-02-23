"use client";
import React, { useTransition } from "react";
import WrapBox from "@/components/ui/wrap.box";
import { Button } from "@mui/material";
import PublicOutlinedIcon from "@mui/icons-material/PublicOutlined";
import { useTranslations } from "next-intl";
import FilesUploadButton from "@/components/ui/buttons/files.upload.button";
import { useGrammarFilesUpload } from "@/features/create-lesson/contexts/grammar.files.upload";
import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import { useRouter } from "@/i18n/navigation";
import { useSearchParams } from "next/navigation";
import { parsePositiveInt } from "@/utils/parse.util";
import { createGrammarLessons } from "@/services/lesson.service";
import { toast } from "react-toastify";

const CreateGrammarLessonForm = () => {
    const t = useTranslations();
    const { setFiles, errorMessage, setErrorMessage, files } =
        useGrammarFilesUpload();

    const { replace } = useRouter();
    const searchParams = useSearchParams();

    const folder = searchParams.get("folder");
    const folderId = parsePositiveInt(folder?.split("-").pop());

    const [isPending, startTransition] = useTransition();

    const handleSubmit = async (event: React.SubmitEvent<HTMLFormElement>) => {
        event.preventDefault();

        startTransition(async () => {
            const response = await createGrammarLessons(folderId, files);
            if (response.statusCode === 201) {
                if (folder) {
                    replace({
                        pathname: "/your-library/folder/[slug]",
                        params: { slug: folder },
                    });
                } else {
                    replace("/your-library/lesson");
                }
            } else {
                toast.error(response.clientMessage, {
                    hideProgressBar: true,
                    closeOnClick: true,
                    autoClose: 2000,
                });
            }
        });
    };

    return (
        <WrapBox>
            <form onSubmit={handleSubmit}>
                <div className="mb-3 flex items-center justify-between">
                    <h1 className="font-semibold">
                        {t("Pages.yourLibrary.lesson.createNewGrammarLesson")}
                    </h1>
                    <Button
                        color="primary"
                        variant="contained"
                        type="submit"
                        loading={isPending}
                    >
                        {t("Common.create")}
                    </Button>
                </div>

                <div className="flex items-center gap-x-3">
                    <FilesUploadButton
                        setFiles={setFiles}
                        errorMessage={errorMessage}
                        setErrorMessage={setErrorMessage}
                    />

                    <Button
                        variant="outlined"
                        color="primary"
                        sx={{ width: "max-content", columnGap: "8px" }}
                    >
                        <PublicOutlinedIcon />
                        {t("Common.scope.public")}
                    </Button>
                </div>
                {errorMessage !== "" && (
                    <span className="text-tc-error mt-1 ml-1 flex items-center gap-x-1 text-[12px] font-semibold">
                        <WarningAmberOutlinedIcon sx={{ fontSize: "14px" }} />
                        {errorMessage}
                    </span>
                )}
            </form>
        </WrapBox>
    );
};

export default CreateGrammarLessonForm;
