"use server";
import { FolderLessonRequest } from "@/types/api/requests/lesson.request";
import { ApiResponse } from "@/types/api/responses/base.response";
import { FolderResponse } from "@/types/api/responses/common.response";
import { sendRequest } from "@/lib/send.request";
import { revalidateTag } from "next/cache";

export const getFolderById = async (id: string): Promise<FolderResponse> => {
    const response = await sendRequest<ApiResponse<FolderResponse>>({
        url: `/folder/${id}`,
        method: "GET",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const getAllFolders = async (): Promise<FolderResponse[]> => {
    const response = await sendRequest<ApiResponse<FolderResponse[]>>({
        url: "/folder/all",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const deleteFolder = async (id: string | number): Promise<number> => {
    const response = await sendRequest<ApiResponse<number>>({
        url: `/folder/${id}`,
        method: "DELETE",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    revalidateTag("pin-folders", "max");
    return response.data;
};

export const addLessonToFolder = async (
    request: FolderLessonRequest,
): Promise<void> => {
    const response = await sendRequest<ApiResponse<void>>({
        url: "/folder/lesson",
        method: "POST",
        body: request,
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const removeLessonFromFolder = async (
    request: FolderLessonRequest,
): Promise<void> => {
    const response = await sendRequest<ApiResponse<void>>({
        url: "/folder/lesson",
        method: "DELETE",
        body: request,
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const createFolder = async (
    folderName: string,
): Promise<FolderResponse> => {
    const response = await sendRequest<ApiResponse<FolderResponse>>({
        url: "/folder",
        method: "POST",
        body: {
            folderName,
        },
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const pinAndUnpinFolderFromSidebar = async (
    folderId: number | string,
): Promise<FolderResponse> => {
    const response = await sendRequest<ApiResponse<FolderResponse>>({
        url: `/folder/pin-and-unpin/${folderId}`,
        method: "PATCH",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const getAllPinFolders = async (): Promise<FolderResponse[]> => {
    const response = await sendRequest<ApiResponse<FolderResponse[]>>({
        url: "/folder/pin",
        nextOption: {
            tags: ["pin-folders"],
        },
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};
