"use server";

import { IImportKanjiDataState } from "@/features/create-course/types/state.type";

export async function importKanjiData(
    initialState: IImportKanjiDataState | null,
    formData: FormData,
): Promise<IImportKanjiDataState> {
    const kanjiData = formData.get("kanji-data")?.toString() || "";
    console.log(kanjiData);
    return initialState!;
}
