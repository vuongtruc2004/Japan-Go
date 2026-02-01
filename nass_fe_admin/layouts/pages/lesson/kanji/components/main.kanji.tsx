import KanjiVgDraw from "../features/kanji.vg.draw";

const MainKanji = ({ kanji }: { kanji: KanjiResponse }) => {
    return (
        <div>
            <div className="border-bdc-primary bg-bgc-page relative mb-3 flex items-center justify-center rounded-md border">
                <KanjiVgDraw svg={kanji.svg} />
                <span className="text-tc-highlight absolute bottom-0 left-1/2 -translate-x-1/2 text-lg font-semibold">
                    「 {kanji.mainSinoVietnamese} 」
                </span>
            </div>

            {kanji.onyomiList.length > 0 && (
                <>
                    <p className="text-tc-highlight font-semibold">Âm On:</p>
                    {kanji.onyomiList.map((onyomi) => {
                        return <p key={onyomi}>{onyomi}</p>;
                    })}
                </>
            )}

            {kanji.kunyomiList.length > 0 && (
                <>
                    <p className="text-tc-highlight font-semibold">Âm Kun:</p>
                    {kanji.kunyomiList.map((kunyomi) => {
                        return <p key={kunyomi}>{kunyomi}</p>;
                    })}
                </>
            )}
        </div>
    );
};

export default MainKanji;
