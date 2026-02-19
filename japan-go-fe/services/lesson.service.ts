import { sendRequest } from "@/lib/send.request";
import {
    ApiResponse,
    PageDetailsResponse,
} from "@/types/api/responses/base.response";
import { LessonResponse } from "@/types/api/responses/lesson.response";

export const getAllLessons = async (): Promise<
    PageDetailsResponse<LessonResponse[]>
> => {
    const response = await sendRequest<
        ApiResponse<PageDetailsResponse<LessonResponse[]>>
    >({
        url: `/lesson`,
        method: "GET",
        queryParams: {
            page: 1,
            size: 5,
        },
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }

    return response.data;
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
