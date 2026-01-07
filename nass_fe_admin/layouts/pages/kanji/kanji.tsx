import KanjiExplore from "./components/kanji.explore";
import KanjiSearchBox from "./features/kanji.search.box";

const Kanji = () => {
    return (
        <div className="flex flex-col gap-y-5">
            <KanjiSearchBox />
            <KanjiExplore />
        </div>
    );
};

export default Kanji;
