"use server"

import { sendRequest } from "@/utils/fetch.api";

export async function folderCreate(initialState: IFolderCreateState | null, formData: FormData): Promise<IFolderCreateState> {
    const folderName = formData.get("folder-name")?.toString().trim() || "";
    const state: IFolderCreateState = {
        folderName: {
            errorMessage: "",
            isError: false,
            value: folderName
        }
    }
    if (folderName.length === 0) {
        state.folderName.isError = true;
        state.folderName.errorMessage = "Tên thư mục không được để trống!";
    } else {
        const response = await sendRequest<ApiResponse<FolderResponse>>({
            url: "/folder",
            method: "POST",
            body: {
                folderName
            }
        });
        state.response = response;
    }
    return state;
}