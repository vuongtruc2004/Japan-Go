"use server";

import { VocabularyRequest } from "@/types/api/requests/vocabulary.request";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import {
    ICreateKanjiLessonState,
    IImportKanjiDataState,
} from "@/features/create-lesson/types/create.lesson.state.type";
import { KanjiLessonRequest } from "@/types/api/requests/lesson.request";
import { LessonType } from "@/types/enums/lesson.enum";
import { createKanjiLesson } from "@/services/lesson.service";

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

export async function submitCreateKanjiLesson(
    formData: FormData,
    kanjiPages: KanjiPageRequest[],
    folderId: number | null,
    bookId: number,
): Promise<ICreateKanjiLessonState> {
    const formState = buildCreateLessonState(formData);

    const body: KanjiLessonRequest = {
        folderId,
        bookId,
        lessonName: formState.lessonName.value,
        description: formState.description.value,
        lessonType: LessonType.KANJI,
        kanjiPages,
    };

    const lesson = await createKanjiLesson(body);
    formState.success = true;
    formState.lesson = lesson;
    return formState;
}

function buildCreateLessonState(formData: FormData): ICreateKanjiLessonState {
    const lessonName = formData.get("lesson-name")?.toString().trim() || "";
    const description = formData.get("description")?.toString().trim() || "";

    const formState: ICreateKanjiLessonState = {
        lessonName: { value: lessonName, isError: false, errorMessage: "" },
        description: { value: description, isError: false, errorMessage: "" },
        success: false,
    };

    if (lessonName.length === 0) formState.lessonName.isError = true;

    if (formState.lessonName.isError) {
        throw new Error(formState.lessonName.errorMessage);
    }

    return {
        ...formState,
    };
}
