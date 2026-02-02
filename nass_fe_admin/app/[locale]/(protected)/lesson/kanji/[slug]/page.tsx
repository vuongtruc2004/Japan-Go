import LessonKanjiHeader from "@/layouts/pages/lesson/kanji/components/lesson.kanji.header";
import LessonKanjiSlide from "@/layouts/pages/lesson/kanji/components/lesson.kanji.slide";
import MainKanji from "@/layouts/pages/lesson/kanji/components/main.kanji";
import SlideSwitchButtons from "@/layouts/pages/lesson/kanji/features/kanji.slide.switch.buttons";
import { getLessonById } from "@/libs/api/lesson/lesson";
import { KanjiSlideWrapper } from "@/libs/wrapper/context/kanji.slide.wrapper";
import { VocabularyVisibilityWrapper } from "@/libs/wrapper/context/vocabulary.visibility.wrapper";
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
        <KanjiSlideWrapper lesson={lesson}>
            <VocabularyVisibilityWrapper>
                <LessonKanjiHeader />
                <SlideSwitchButtons />
                <div className="flex items-start gap-x-3">
                    <MainKanji />
                    <LessonKanjiSlide />
                </div>
            </VocabularyVisibilityWrapper>
        </KanjiSlideWrapper>
    );
};

export default KanjiLessonDetailsPage;
