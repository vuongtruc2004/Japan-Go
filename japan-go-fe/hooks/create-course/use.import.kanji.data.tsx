"use client";
import { useActionState } from "react";
import { IImportKanjiDataState } from "@/features/create-course/types/state.type";
import { importKanjiData } from "@/features/create-course/actions/create.course.actions";

const initialState: IImportKanjiDataState | null = null;

export function useImportKanjiData() {
    const [state, formAction, pending] = useActionState(
        importKanjiData,
        initialState,
    );
    return { state, formAction, pending };
}
