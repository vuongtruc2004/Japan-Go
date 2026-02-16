"use client";
import { useActionState } from "react";
import { IImportKanjiDataState } from "@/features/create-course/types/create.course.state.type";
import { importKanjiDataActions } from "@/features/create-course/actions/create.course.actions";

const initialState: IImportKanjiDataState | null = null;

export function useImportKanjiData() {
    const [state, dispatchAction, isPending] = useActionState(
        importKanjiDataActions,
        initialState,
    );
    return { state, dispatchAction, isPending };
}
