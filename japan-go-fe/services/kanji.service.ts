import { GetSinoVietnameseRequest } from "@/types/api/requests/sino.vietnamese.request";
import { sendRequest } from "@/lib/send.request";

export const getSinoVietnameseOfKanji = async (
    request: GetSinoVietnameseRequest,
) => {
    return await sendRequest<string>({
        url: "/sino-vietnamese",
        method: "POST",
        body: request,
        responseType: "text",
    });
};
