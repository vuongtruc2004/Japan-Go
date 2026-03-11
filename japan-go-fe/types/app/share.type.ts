export interface ValidateField {
    isError: boolean;
    errorMessage: string;
    value: string;
}

export interface PageRequest {
    pageNumber: number | string;
    pageSize: number | string;
}
