export interface BaseResponse<TKey> {
    id: TKey;
    createdBy: string;
    createdTime: string;
    modifiedBy: string;
    modifiedTime: string;
}

export interface ApiResponse<T> {
    statusCode: number;
    devMessage: string;
    clientMessage: string;
    data: T;
}

export interface PageDetailsResponse<T> {
    currentPage: number;
    pageSize: number;
    totalPages: number;
    totalElements: number;
    content: T;
}