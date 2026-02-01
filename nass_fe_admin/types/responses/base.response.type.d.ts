interface BaseResponse<TKey> {
    id: TKey;
    createdBy: string;
    createdTime: string;
    modifiedBy: string;
    modifiedTime: string;
}

interface ApiResponse<T> {
    statusCode: number;
    devMessage: string;
    clientMessage: string;
    data: T;
}

interface PageDetailsResponse<T> {
    currentPage: number;
    pageSize: number;
    totalPages: number;
    totalElements: number;
    content: T;
}