import { sendRequest } from "@/lib/send.request";
import { PageRequest } from "@/types/app/share.type";
import { GrammarSearchRequest } from "@/types/api/requests/grammar.request";
import {
    ApiResponse,
    PageDetailsResponse,
} from "@/types/api/responses/base.response";
import { GrammarResponse } from "@/types/api/responses/grammar.response";

export const exportAllGrammarsInFolder = async (folderId: string | number) => {
    const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}/grammar/export/${folderId}`,
    );
    const blob = await response.blob();

    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "grammars.xlsx";
    a.click();
};

export const getAllGrammars = async (
    searchRequest: GrammarSearchRequest,
    pageRequest: PageRequest,
): Promise<ApiResponse<PageDetailsResponse<GrammarResponse[]>>> => {
    const response = await sendRequest<
        ApiResponse<PageDetailsResponse<GrammarResponse[]>>
    >({
        url: "/grammar",
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        queryParams: pageRequest,
        body: searchRequest,
    });

    if (response.statusCode !== 200) {
        throw new Error(response.clientMessage);
    }

    return response;
};
