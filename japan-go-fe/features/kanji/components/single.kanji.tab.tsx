import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";

const SingleKanjiTab = () => {
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

