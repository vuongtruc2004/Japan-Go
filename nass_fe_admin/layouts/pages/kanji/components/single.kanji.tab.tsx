import Empty from "@/components/common/empty";
import { useTranslations } from "next-intl";
import { IKanjiTab } from "../models/kanji.type";

const SingleKanjiTab = ({ activeTab }: { activeTab: IKanjiTab }) => {
    const t = useTranslations("Pages.kanji.explore");
    return (
        // <div className="grid grid-cols-10 gap-3">
        //     {Array.from({ length: 0 }).map((_, index) => {
        //         return <SingleKanjiBox key={index} activeTab={activeTab} />;
        //     })}
        // </div>
        <Empty text={t("empty")} />
    );
};

export default SingleKanjiTab;
