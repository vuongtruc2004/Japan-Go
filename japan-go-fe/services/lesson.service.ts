import { sendRequest } from "@/lib/send.request";
import { ApiResponse, PageDetailsResponse } from "@/types/api/responses/base.response";
import { BookResponse, LessonResponse } from "@/types/api/responses/lesson.response";
import { GrammarLessonRequest, KanjiLessonRequest } from "@/types/api/requests/lesson.request";
import { API_URL } from "@/utils/url";

export const getAllLessons = async () => {
    const response = await sendRequest<
        ApiResponse<PageDetailsResponse<LessonResponse[]>>
    >({
        url: `/lessons`,
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
        url: `/lessons/${id}`,
        method: "GET",
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const deleteLesson = async (id: string | number): Promise<number> => {
    const response = await fetch(`${API_URL}/lessons/${id}`, {
        method: "DELETE",
    });
    const result: ApiResponse<number> = await response.json();
    if (result.statusCode !== 200) {
        throw new Error(result.clientMessage);
    }
    return result.data;
};

export const deleteAllLessons = async () => {
    const response = await sendRequest<ApiResponse<void>>({
        url: `/lessons/all`,
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
        url: "/kanji-lessons",
        method: "POST",
        body,
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response.data;
};

export const createGrammarLessons = async (
    request: GrammarLessonRequest,
): Promise<ApiResponse<LessonResponse[]>> => {
    const formData = new FormData();

    if (request.folderId !== null) {
        formData.append("folderId", String(request.folderId));
    }

    formData.append("bookId", String(request.bookId));
    formData.append("jlptLevel", String(request.jlptLevel));

    request.files.forEach((file) => {
        formData.append("files", file);
    });

    return await sendRequest<ApiResponse<LessonResponse[]>>({
        url: "/grammar-lessons",
        method: "POST",
        body: formData,
    });
};

export const getAllBooks = async () => {
    const response = await sendRequest<ApiResponse<BookResponse[]>>({
        url: `/books`,
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response;
};
