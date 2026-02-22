"use server";
import queryString from "query-string";

type HttpMethod = "GET" | "POST" | "PUT" | "PATCH" | "DELETE";

interface SendRequestProps {
    url: string;
    method?: HttpMethod;
    body?: unknown;
    queryParams?: unknown;
    useCredentials?: boolean;
    headers?: Record<string, string>;
    nextOption?: NextFetchRequestConfig;
}

const API_BASE_URL =
    process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:2509/api/v1";

export async function sendRequest<TResponse>(
    props: SendRequestProps,
): Promise<TResponse> {
    let url = API_BASE_URL + props.url;

    const {
        method = "GET",
        body,
        queryParams,
        useCredentials = false,
        headers,
        nextOption,
    } = props;

    const finalHeaders: Record<string, string> = { ...(headers || {}) };

    const options: RequestInit = {
        method,
        headers: finalHeaders,
        body:
            body instanceof FormData
                ? body
                : body
                  ? JSON.stringify(body)
                  : undefined,
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
    return (await res.json()) as TResponse;
}
