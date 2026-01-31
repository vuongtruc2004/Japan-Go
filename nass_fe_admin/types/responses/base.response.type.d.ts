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
    userMessage: string;
    data: T;
}
