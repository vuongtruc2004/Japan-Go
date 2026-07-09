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
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [prevLevel, setPrevLevel] = useState(activeTab.level);
    const t = useTranslations("Pages.kanji.explore");

    if (activeTab.level !== prevLevel) {
        setPrevLevel(activeTab.level);
        setIsLoading(true);
    }

    useEffect(() => {
        getKanjiByJlptLevel(activeTab.level)
            .then((response) => {
                setKanjiData([...response.data]);
            })
            .catch((error) => {
                console.error("Failed to load kanji:", error);
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, [activeTab.level]);

    if (isLoading) {
        return (
            <div className="flex flex-wrap items-center gap-3">
                {Array.from({ length: 15 }).map((_, index) => (
                    <div
                        key={index}
                        className="aspect-square h-15 w-15 shrink-0 rounded-md border border-bdc-primary bg-neutral-100 dark:bg-zinc-800/50 animate-pulse"
                    />
                ))}
            </div>
        );
    }

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
