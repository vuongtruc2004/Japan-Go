import { getTranslations } from "next-intl/server";
import WrapBox from "@/components/ui/wrap.box";
import FormatQuizletButton from "@/features/deck/components/format.quizlet.button";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "Common.links" });

    return {
        title: t("flashcard"),
    };
}

const FlashCardPage = () => {
    return (
        <WrapBox>
            <FormatQuizletButton />
        </WrapBox>
    );
};

export default FlashCardPage;
