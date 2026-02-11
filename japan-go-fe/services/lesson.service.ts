import { sendRequest } from "@/lib/send.request";
import { ApiResponse, PageDetailsResponse } from "@/types/api/responses/base.response";
import { LessonDetailsResponse, LessonResponse } from "@/types/api/responses/lesson.response";

export const getAllLessons = async (): Promise<PageDetailsResponse<LessonResponse[]>> => {
    const response = await sendRequest<ApiResponse<PageDetailsResponse<LessonResponse[]>>>({
        url: `/lesson`,
        method: "GET",
        queryParams: {
            page: 1,
            size: 5
        }
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }

    return response.data;
}

export const getLessonById = async (id: string): Promise<LessonDetailsResponse> => {
    const response = await sendRequest<ApiResponse<LessonDetailsResponse>>({
        url: `/lesson/${id}`,
        method: "GET"
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response.data;
}

