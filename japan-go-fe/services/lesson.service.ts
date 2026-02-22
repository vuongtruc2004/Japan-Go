import { sendRequest } from "@/lib/send.request";
import {
    ApiResponse,
    PageDetailsResponse,
} from "@/types/api/responses/base.response";
import { LessonResponse } from "@/types/api/responses/lesson.response";
import { KanjiLessonRequest } from "@/types/api/requests/lesson.request";

export const getAllLessons = async () => {
    const response = await sendRequest<
        ApiResponse<PageDetailsResponse<LessonResponse[]>>
    >({
        url: `/lesson`,
        method: "GET",
        queryParams: {
            page: 1,
            size: 100,
        },
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }

    return response;
};

export const getLessonById = async (id: string): Promise<LessonResponse> => {
    const response = await sendRequest<ApiResponse<LessonResponse>>({
        url: `/lesson/${id}`,
        method: "GET",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const deleteLesson = async (id: string | number): Promise<number> => {
    const response = await sendRequest<ApiResponse<number>>({
        url: `/lesson/${id}`,
        method: "DELETE",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const deleteAllLessons = async () => {
    const response = await sendRequest<ApiResponse<void>>({
        url: `/lesson/all`,
        method: "DELETE",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
};

export const createKanjiLesson = async (
    body: KanjiLessonRequest,
): Promise<LessonResponse> => {
    const response = await sendRequest<ApiResponse<LessonResponse>>({
        url: "/kanji-lesson",
        method: "POST",
        body,
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const createGrammarLessons = async (
    folderId: number | null,
    files: File[],
): Promise<ApiResponse<LessonResponse[]>> => {
    const formData = new FormData();

    if (folderId !== null) {
        formData.append("folderId", String(folderId));
    }

    files.forEach((file) => {
        formData.append("files", file);
    });

    return await sendRequest<ApiResponse<LessonResponse[]>>({
        url: "/grammar-lesson",
        method: "POST",
        body: formData,
    });
};
