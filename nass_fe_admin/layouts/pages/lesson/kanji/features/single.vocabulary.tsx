const SingleVocabulary = ({
    vocabulary,
}: {
    vocabulary: VocabularyResponse;
}) => {
    return (
        <div className="flex items-center justify-start gap-x-3">
            <div className="border-bdc-primary bg-bgc-page font-noto-sans-jp rounded-md border px-4 py-2 text-3xl">
                {vocabulary.japanese}
            </div>
            <div className="border-bdc-primary bg-bgc-page font-noto-sans-jp rounded-md border px-4 py-2 text-xl">
                {vocabulary.reading}
            </div>
            <div className="border-bdc-primary bg-bgc-page rounded-md border px-4 py-2">
                {vocabulary.vietnamese}
            </div>
        </div>
    );
};

export default SingleVocabulary;
