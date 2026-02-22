"use client";
import { folderCreate } from "@/actions/folder.actions";
import { IFolderCreateState } from "@/components/domain/folder/folder.type";
import { useActionState } from "react";

const initialState: IFolderCreateState | null = null;

export function useFolderCreate() {
  const [state, formAction, pending] = useActionState(folderCreate, initialState);
  return { state, formAction, pending };
}
