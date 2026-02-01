"use client";
import { useRouter } from "@/i18n/navigation";
import { useActiveKanjiSlide } from "@/libs/wrapper/context/active.kanji.slide.wrapper";
import TranslateIcon from "@mui/icons-material/Translate";

const LessonKanjiHeader = () => {
    const { back } = useRouter();
    const { lesson } = useActiveKanjiSlide();

    return (
        <div className="bg-bgc-app border-bdc-primary mb-3 rounded-md border p-5">
            <div
                onClick={() => back()}
                className="group flex w-max flex-1 cursor-pointer items-center gap-x-3"
            >
                <span className="flex h-12 w-12 items-center justify-center rounded-md bg-green-500/10 text-green-700 dark:text-green-500">
                    <TranslateIcon />
                </span>
                <div>
                    <p className="text-tc-primary group-hover:text-tc-highlight font-semibold group-hover:underline">
                        {lesson.lessonName}
                    </p>
                    <p className="text-tc-muted text-sm font-semibold">
                        {lesson.lessonType === "KANJI"
                            ? "Bài giảng chữ Hán"
                            : "Bài giảng ngữ pháp"}
                        ・Số slide: {lesson.kanjiLesson.kanjiPages.length}・ Tác
                        giả: Bạn
                    </p>
                </div>
            </div>
        </div>
    );
};

export default LessonKanjiHeader;
