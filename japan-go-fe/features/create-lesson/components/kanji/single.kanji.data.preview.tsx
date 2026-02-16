import React from "react";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import { Divider } from "@mui/material";

const SingleKanjiDataPreview = ({
    kanjiPage,
}: {
    kanjiPage: KanjiPageRequest;
}) => {
    return (
        <div className="border-bdc-muted flex items-center gap-x-5 rounded-md border p-5">
            <span className="border-bdc-muted flex aspect-square h-20 w-20 items-center justify-center rounded-md border text-3xl">
                {kanjiPage.mainKanjiCharacter}
            </span>

            <Divider orientation="vertical" variant="middle" flexItem />

            <ul className="flex flex-col gap-y-3">
                {kanjiPage.vocabularies.map((vocabulary) => {
                    return (
                        <li
                            key={vocabulary.japanese + "-" + vocabulary.reading}
                            className="flex items-center gap-x-3"
                        >
                            <p>{vocabulary.japanese}</p>
                            <p>{vocabulary.reading}</p>
                            <p>{vocabulary.meaning}</p>
                            <p>{vocabulary.note}</p>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
};

export default SingleKanjiDataPreview;
