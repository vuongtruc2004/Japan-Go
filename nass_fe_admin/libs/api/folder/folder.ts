import { sendRequest } from "@/utils/fetch.api";

export const getFolderById = async (id: string): Promise<FolderDetailsResponse> => {
    const response = await sendRequest<ApiResponse<FolderDetailsResponse>>({
        url: `/folder/${id}`,
        method: "GET",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
}

export const getAllFolders = async (): Promise<FolderResponse[]> => {
    const response = await sendRequest<ApiResponse<FolderResponse[]>>({
        url: "/folder/all",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
}