"use client";
import Empty from "@/components/ui/empty";
import { useTranslations } from "next-intl";
import { useActiveKanjiTab } from "@/features/kanji/contexts/active.kanji.tab";
import SingleKanjiBox from "@/features/kanji/components/single.kanji.box";
import { getKanjiByJlptLevel } from "@/services/kanji.service";
import { useEffect, useState } from "react";
import { KanjiResponse } from "@/types/api/responses/kanji.response";

const SingleKanjiTab = () => {
    const { activeTab } = useActiveKanjiTab();
    const [kanjiData, setKanjiData] = useState<KanjiResponse[]>([]);
    const t = useTranslations("Pages.kanji.explore");

    useEffect(() => {
        getKanjiByJlptLevel(activeTab.level).then((response) => {
            setKanjiData([...response.data]);
        });
    }, [activeTab, setKanjiData]);

    if (kanjiData.length === 0) {
        return <Empty text={t("empty")} />;
    }

    return (
        <div className="flex flex-wrap items-center gap-3">
            {kanjiData.map((kanji) => {
                return <SingleKanjiBox kanji={kanji} key={kanji.id} />;
            })}
        </div>
    );
};

export default SingleKanjiTab;
