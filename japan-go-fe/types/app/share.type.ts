export interface ValidateField {
    isError: boolean;
    errorMessage: string;
    value: string;
}

export interface PageRequest {
    page: number | string;
    size: number | string;
}
