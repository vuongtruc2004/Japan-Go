import { IKanjiTab } from "../types/kanji.ui.type";

const SingleKanjiBox = ({ activeTab }: { activeTab: IKanjiTab }) => {
    return (
        <div
            className={`${activeTab.colorStyle} group flex aspect-square cursor-pointer items-center justify-center rounded-md border`}
        >
            <p className="font-noto-sans-jp text-xl transition-all duration-100 group-hover:scale-150">
                私
            </p>
        </div>
    );
};

export default SingleKanjiBox;
