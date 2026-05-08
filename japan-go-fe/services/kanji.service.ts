import { GetSinoVietnameseRequest } from "@/types/api/requests/sino.vietnamese.request";
import { sendRequest } from "@/lib/send.request";
import { KanjiResponse } from "@/types/api/responses/kanji.response";
import { ApiResponse } from "@/types/api/responses/base.response";

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

export const getKanjiByJlptLevel = async (jlptLevel: number) => {
    const response = await sendRequest<ApiResponse<KanjiResponse[]>>({
        url: "/kanji/level",
        method: "GET",
        queryParams: {
            "jlpt-level": jlptLevel,
        },
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response;
};

export const getKanjiByKanjiCharacter = async (kanjiCharacter: string) => {
    const response = await sendRequest<ApiResponse<KanjiResponse>>({
        url: "/kanji/character",
        method: "GET",
        queryParams: {
            "kanji-character": kanjiCharacter,
        },
    });
    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }
    return response;
};

export const updateKanjiMainSinoVietnamese = async (
    kanjiId: number,
    sinoVietnameseId: number,
) => {
    const response = await sendRequest<ApiResponse<KanjiResponse>>({
        url: "/kanji/main-sino-vietnamese",
        method: "PATCH",
        body: {
            kanjiId,
            sinoVietnameseId,
        },
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response;
};
