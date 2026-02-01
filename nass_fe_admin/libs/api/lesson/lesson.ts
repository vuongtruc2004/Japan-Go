import { sendRequest } from "@/utils/fetch.api";

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

export const addLessonToFolder = async (request: FolderLessonRequest): Promise<FolderResponse> => {
    const response = await sendRequest<ApiResponse<FolderResponse>>({
        url: "/folder/lesson",
        method: "POST",
        body: request
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response.data;
}