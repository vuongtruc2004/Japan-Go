import { useVocabularyVisibility } from "@/libs/wrapper/context/vocabulary.visibility.wrapper";

const SingleVocabulary = ({
    vocabulary,
}: {
    vocabulary: VocabularyResponse;
}) => {
    const { showJapanese, showReading, showMeaning } =
        useVocabularyVisibility();

    if (!showJapanese && !showReading && !showMeaning) return;
    return (
        <div className="flex items-center justify-start gap-x-3">
            {showJapanese && (
                <div className="border-bdc-primary bg-bgc-page font-noto-sans-jp rounded-md border px-4 py-2 text-3xl">
                    {vocabulary.japanese}
                </div>
            )}
            {showReading && (
                <div className="border-bdc-primary bg-bgc-page font-noto-sans-jp rounded-md border px-4 py-2 text-xl">
                    {vocabulary.reading}
                </div>
            )}
            {showMeaning && (
                <div className="border-bdc-primary bg-bgc-page rounded-md border px-4 py-2">
                    {vocabulary.vietnamese}
                </div>
            )}
        </div>
    );
};

export default SingleVocabulary;
