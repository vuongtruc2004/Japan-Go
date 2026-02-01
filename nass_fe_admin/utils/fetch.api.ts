'use server'
import queryString from "query-string";

type ResponseType = "json" | "text" | "blob";

interface IProps {
    url: string;
    method?: "GET" | "POST" | "PUT" | "PATCH" | "DELETE";
    body?: { [key: string]: any } | FormData;
    queryParams?: any;
    useCredentials?: boolean;
    headers?: Record<string, string>;
    nextOption?: RequestInit;
    responseType?: ResponseType;
}

export const sendRequest = async <T>(props: IProps): Promise<T> => {
    let url = "http://localhost:2509/api/v1" + props.url;
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
        body: body instanceof FormData ? body : body ? JSON.stringify(body) : undefined,
        ...nextOption,
    };

    // Nếu gửi JSON thì set content-type (trừ FormData)
    if (!(body instanceof FormData) && body && !finalHeaders["Content-Type"]) {
        finalHeaders["Content-Type"] = "application/json";
    }

    if (useCredentials) options.credentials = "include";

    if (queryParams) url = `${url}?${queryString.stringify(queryParams)}`;

    const res = await fetch(url, options);

    if (!res.ok) {
        const msg = await res.text().catch(() => "");
        throw new Error(`HTTP ${res.status}: ${msg}`);
    }

    if (responseType === "blob") return (await res.blob()) as T;
    if (responseType === "text") return (await res.text()) as T;
    return (await res.json()) as T;
};
