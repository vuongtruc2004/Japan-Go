import { KanjiResponse } from "@/types/api/responses/kanji.response";
import { useActiveKanjiTab } from "@/features/kanji/contexts/active.kanji.tab";
import { Link } from "@/i18n/navigation";

const SingleKanjiBox = ({ kanji }: { kanji: KanjiResponse }) => {
    const { activeTab } = useActiveKanjiTab();

    return (
        <Link
            href={{
                pathname: "/kanji/details",
                query: {
                    searchKey: kanji.kanjiCharacter,
                },
            }}
            className={`${activeTab.colorStyle} group flex aspect-square h-15 w-15 shrink-0 cursor-pointer items-center justify-center rounded-md border`}
        >
            <p className="font-noto-sans-jp text-2xl transition-all duration-150 group-hover:scale-150">
                {kanji.kanjiCharacter}
            </p>
        </Link>
    );
};

export default SingleKanjiBox;
