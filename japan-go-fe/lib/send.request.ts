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

export async function sendRequest<TResponse>(
    props: SendRequestProps,
): Promise<TResponse> {
    const baseUrl =
        process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:2509/api/v1";

    let url = `${baseUrl}${props.url}`;

    const {
        method = "GET",
        body,
        queryParams,
        useCredentials = false,
        headers,
        nextOption,
    } = props;

    const finalHeaders: Record<string, string> = { ...(headers || {}) };

    if (!(body instanceof FormData) && body && !finalHeaders["Content-Type"]) {
        finalHeaders["Content-Type"] = "application/json";
    }

    const options: RequestInit = {
        method,
        headers: finalHeaders,
        body:
            body instanceof FormData
                ? body
                : body
                  ? JSON.stringify(body)
                  : undefined,
        credentials: useCredentials ? "include" : undefined,
        ...nextOption,
    };

    if (queryParams) {
        url = `${url}?${queryString.stringify(queryParams)}`;
    }

    const res = await fetch(url, options);

    if (!res.ok) {
        throw new Error(`HTTP ${res.status}: ${res.statusText}`);
    }

    return (await res.json()) as TResponse;
}
