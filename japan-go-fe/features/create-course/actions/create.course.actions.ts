"use server";

import { IImportKanjiDataState } from "@/features/create-course/types/create.course.state.type";
import { VocabularyRequest } from "@/types/api/requests/vocabulary.request";

export async function importKanjiDataActions(
    formData: FormData | null,
): Promise<IImportKanjiDataState | null> {
    if (!formData) return null;

    const kanjiData = String(formData.get("kanji-data") ?? "");

    const formState: IImportKanjiDataState = {
        kanjiData: { value: kanjiData, isError: false, errorMessage: "" },
        kanjiPages: [],
        success: false,
    };

    const rows = kanjiData.split(/\r?\n/);
    const map = new Map<string, VocabularyRequest[]>();

    for (const raw of rows) {
        const row = raw.trim();
        if (!row) continue;

        const cols = row.split("\t");
        const [mainKanjiCharacter, japanese, reading, meaning, note = ""] =
            cols;

        const vocabulary: VocabularyRequest = {
            japanese: japanese.trim(),
            reading: reading.trim(),
            meaning: meaning.trim(),
            note: note.trim(),
        };

        const vocabularies = map.get(mainKanjiCharacter);
        if (vocabularies) vocabularies.push(vocabulary);
        else map.set(mainKanjiCharacter, [vocabulary]);
    }

    formState.kanjiPages = Array.from(map, ([key, value]) => ({
        mainKanjiCharacter: key,
        vocabularies: value,
    }));

    formState.success = true;

    await new Promise((resolve) => setTimeout(resolve, 1000));
    return formState;
}
