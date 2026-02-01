"use client";
import Empty from "@/components/common/empty";
import { Link } from "@/i18n/navigation";
import { useFolderDetails } from "@/libs/wrapper/context/folder.details.wrapper";
import { slugifyText } from "@/utils/slugify.text";
import AbcIcon from "@mui/icons-material/Abc";
import TranslateIcon from "@mui/icons-material/Translate";
import LessonMoreButton from "../features/lesson.more.button";

const LessonList = () => {
    const { folder } = useFolderDetails();
    return (
        <div>
            {folder.lessons && folder.lessons.length > 0 ? (
                folder.lessons.map((lesson) => {
                    return (
                        <div
                            key={lesson.id}
                            className="hover:bg-hbgc-app flex items-center justify-between rounded-md pr-4 transition-all duration-150"
                        >
                            <Link
                                href={{
                                    pathname: "/lesson/kanji/[slug]",
                                    params: {
                                        slug: slugifyText(
                                            lesson.lessonName + "-" + lesson.id,
                                        ),
                                    },
                                }}
                                className="flex flex-1 items-center gap-x-3 py-4 pl-4"
                            >
                                <span className="flex h-12 w-12 items-center justify-center rounded-md bg-green-500/10 text-green-700 dark:text-green-500">
                                    {lesson.lessonType === "KANJI" ? (
                                        <TranslateIcon />
                                    ) : (
                                        <AbcIcon />
                                    )}
                                </span>
                                <div>
                                    <p className="text-tc-primary font-semibold">
                                        {lesson.lessonName}
                                    </p>
                                    <p className="text-tc-muted text-sm font-semibold">
                                        {lesson.lessonType === "KANJI"
                                            ? "Bài giảng chữ Hán"
                                            : "Bài giảng ngữ pháp"}
                                        ・ Tác giả: Bạn
                                    </p>
                                </div>
                            </Link>

                            <LessonMoreButton lesson={lesson} />
                        </div>
                    );
                })
            ) : (
                <Empty text="Không có bài giảng nào để hiển thị" />
            )}
        </div>
    );
};

export default LessonList;
