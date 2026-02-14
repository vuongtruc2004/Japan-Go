"use server";

import { createFolder } from "@/services/folder.service";
import { IFolderCreateState } from "@/components/domain/folder/folder.type";

export async function folderCreate(
    initialState: IFolderCreateState | null,
    formData: FormData,
): Promise<IFolderCreateState> {
    const folderName = formData.get("folder-name")?.toString().trim() || "";
    const state: IFolderCreateState = {
        folderName: {
            errorMessage: "",
            isError: false,
            value: folderName,
        },
    };
    if (folderName.length === 0) {
        state.folderName.isError = true;
        state.folderName.errorMessage = "Tên thư mục không được để trống!";
    } else {
        state.response = await createFolder(folderName);
    }
    return state;
}
