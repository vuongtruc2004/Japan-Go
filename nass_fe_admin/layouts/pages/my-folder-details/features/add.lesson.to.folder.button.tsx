"use client";
import { useRouter } from "@/i18n/navigation";
import { addLessonToFolder } from "@/libs/api/lesson/lesson";
import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";
import AddIcon from "@mui/icons-material/Add";

const AddLessonToFolderButton = ({ lesson }: { lesson: LessonResponse }) => {
    const { folder } = useFolderDetails();
    const { refresh } = useRouter();

    const handleAddLessonToFolder = async () => {
        await addLessonToFolder({
            folderId: folder.id,
            lessonId: lesson.id,
        });
        refresh();
    };

    return (
        <span
            className="border-bdc-muted flex h-8 w-8 cursor-pointer items-center justify-center rounded-full border"
            onClick={handleAddLessonToFolder}
        >
            <AddIcon fontSize="small" />
        </span>
    );
};

export default AddLessonToFolderButton;
