"use server";
import { sendRequest } from "@/lib/send.request";
import { ApiResponse } from "@/types/api/responses/base.response";
import { DividerType } from "../types/kanji.enum";
import { IGetSinoVietnameseState } from "../types/kanji.state.type";

export async function getSinoVietnamese(
    dividerType: DividerType,
    initialState: IGetSinoVietnameseState | null,
    formData: FormData,
): Promise<IGetSinoVietnameseState> {
    const kanji = formData.get("kanji")?.toString().trim() || "";
    const customValue = formData.get("custom-value")?.toString() || "";

    const state: IGetSinoVietnameseState = {
        kanji: {
            value: kanji,
            errorMessage: "",
            isError: false,
        },
        dividerType,
        customValue,
        sinoVietnamese: "",
    };

    if (kanji === "") {
        state.kanji.isError = true;
        state.kanji.errorMessage = "Kanji cannot be blank!";
    }

    const result = kanji
        .split(/\r?\n/)
        .map((s) => s.trim().toLowerCase())
        .join("\n");
    state.sinoVietnamese = result;
    if (!state.kanji.isError) {
        const response = await sendRequest<ApiResponse<string>>({
            url: "/sino-vietnamese",
            headers: {
                "Content-Type": "application/json",
            },
            method: "POST",
            body: {
                kanjiArrayRaw: kanji,
                divider: dividerType === "custom" ? customValue : dividerType,
            },
        });

        if (response.statusCode === 200) {
            state.sinoVietnamese = response.data;
        }
    }
    return state;
}
