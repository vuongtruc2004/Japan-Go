interface ApiResponse<T> {
    statusCode: number;
    devMessage: string;
    userMessage: string;
    data: T;
}