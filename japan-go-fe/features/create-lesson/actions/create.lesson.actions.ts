"use server";

import { VocabularyRequest } from "@/types/api/requests/vocabulary.request";
import { KanjiPageRequest } from "@/types/api/requests/kanji.request";
import {
    ICreateKanjiLessonState,
    IImportKanjiDataState,
} from "@/features/create-lesson/types/create.lesson.state.type";
import { sendRequest } from "@/lib/send.request";
import { ApiResponse } from "@/types/api/responses/base.response";
import { LessonRequest } from "@/types/api/requests/lesson.request";
import { LessonType } from "@/types/enums/lesson.enum";
import { LessonResponse } from "@/types/api/responses/lesson.response";

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

export async function createKanjiLesson(
    formData: FormData,
    kanjiPages: KanjiPageRequest[],
    folderId: number | null,
): Promise<ICreateKanjiLessonState> {
    const lessonName = formData.get("lesson-name")?.toString().trim() || "";
    const description = formData.get("description")?.toString().trim() || "";

    const formState: ICreateKanjiLessonState = {
        lessonName: { value: lessonName, isError: false, errorMessage: "" },
        description: { value: description, isError: false, errorMessage: "" },
        success: false,
    };

    const body: LessonRequest = {
        folderId,
        lessonName,
        description,
        lessonType: LessonType.KANJI,
        kanjiLesson: {
            kanjiPages,
        },
    };

    const response = await sendRequest<ApiResponse<LessonResponse>>({
        url: "/lesson",
        method: "POST",
        body,
    });

    formState.apiResponse = response;
    if (response.statusCode === 201) {
        formState.success = true;
    } else {
        throw new Error(formState.apiResponse.clientMessage);
    }
    return formState;
}
