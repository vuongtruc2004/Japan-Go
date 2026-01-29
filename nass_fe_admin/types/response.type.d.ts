interface ApiResponse<T> {
    statusCode: number;
    devMessage: string;
    userMessage: string;
    data: T;
}

interface BaseResponse<TKey> {
    id: TKey;
    createdBy: string;
    createdTime: string;
    modifiedBy: string;
    modifiedTime: string;
}

interface FolderResponse extends BaseResponse<number> {
    folderName: string;
}