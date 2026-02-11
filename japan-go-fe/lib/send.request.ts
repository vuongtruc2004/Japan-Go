"use server";
import queryString from "query-string";

type HttpMethod = "GET" | "POST" | "PUT" | "PATCH" | "DELETE";
type ResponseType = "json" | "text" | "blob";

interface SendRequestProps<
    TBody = unknown,
    TQuery = Record<string, string | number | boolean | null | undefined>,
> {
    url: string;
    method?: HttpMethod;
    body?: TBody | FormData;
    queryParams?: TQuery;
    useCredentials?: boolean;
    headers?: Record<string, string>;
    nextOption?: RequestInit;
    responseType?: ResponseType;
}

const API_BASE_URL =
    process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:2509/api/v1";

export async function sendRequest<TResponse, TBody = unknown, TQuery = unknown>(
    props: SendRequestProps<TBody, TQuery>,
): Promise<TResponse> {
    let url = API_BASE_URL + props.url;

    const {
        method = "GET",
        body,
        queryParams,
        useCredentials = false,
        headers,
        nextOption,
        responseType = "json",
    } = props;

    const finalHeaders: Record<string, string> = { ...(headers || {}) };

    const options: RequestInit = {
        method,
        headers: finalHeaders,
        body:
            body instanceof FormData ? body : body ? JSON.stringify(body) : undefined,
        ...nextOption,
    };

    if (!(body instanceof FormData) && body && !finalHeaders["Content-Type"]) {
        finalHeaders["Content-Type"] = "application/json";
    }

    if (useCredentials) options.credentials = "include";

    if (queryParams) {
        url = `${url}?${queryString.stringify(queryParams)}`;
    }

    const res = await fetch(url, options);

    if (!res.ok) {
        const msg = await res.text().catch(() => "");
        throw new Error(`HTTP ${res.status}: ${msg}`);
    }

    if (responseType === "blob") return (await res.blob()) as TResponse;
    if (responseType === "text") return (await res.text()) as TResponse;
    return (await res.json()) as TResponse;
}
