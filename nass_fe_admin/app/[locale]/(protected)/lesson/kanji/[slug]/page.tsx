import LessonKanjiHeader from "@/layouts/pages/lesson/kanji/components/lesson.kanji.header";
import LessonKanjiSlide from "@/layouts/pages/lesson/kanji/components/lesson.kanji.slide";
import SlideSwitchButtons from "@/layouts/pages/lesson/kanji/features/kanji.slide.switch.buttons";
import { getLessonById } from "@/libs/api/lesson/lesson";
import { ActiveKanjiSlideWrapper } from "@/libs/wrapper/context/active.kanji.slide.wrapper";
import { Metadata } from "next";

export async function generateMetadata({
    params,
}: {
    params: Promise<{ slug: string }>;
}): Promise<Metadata> {
    const slug = (await params).slug;
    const id = slug.split("-").pop() || "";
    const lesson = await getLessonById(id);

    return {
        title: lesson.lessonName,
    };
}

const KanjiLessonDetailsPage = async ({
    params,
}: {
    params: Promise<{ slug: string }>;
}) => {
    const slug = (await params).slug;
    const id = slug.split("-").pop() || "";
    const lesson = await getLessonById(id);

    return (
        <ActiveKanjiSlideWrapper lesson={lesson}>
            <LessonKanjiHeader />
            <div className="flex items-start gap-x-3">
                <SlideSwitchButtons />
                <LessonKanjiSlide />
            </div>
        </ActiveKanjiSlideWrapper>
    );
};

export default KanjiLessonDetailsPage;
