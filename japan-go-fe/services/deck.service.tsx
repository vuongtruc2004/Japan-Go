import { DeckRequest } from "@/types/api/requests/deck.request";
import { sendRequest } from "@/lib/send.request";
import { ApiResponse } from "@/types/api/responses/base.response";
import { DeckResponse } from "@/types/api/responses/deck.response";

export const createDeckFromFolder = async (deckRequest: DeckRequest) => {
    const response = await sendRequest<ApiResponse<DeckResponse>>({
        url: "/decks",
        method: "POST",
        body: deckRequest,
    });
    if (response.statusCode !== 201) {
        throw new Error(response.clientMessage);
    }
    return response;
};
