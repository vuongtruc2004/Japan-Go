'use server'
import { sendRequest } from "@/utils/fetch.api";
import { IGetSinoVnState } from "../models/kanji.type";

export async function getSinoVn(dividerType: "line" | "whitespace" | "custom", initialState: IGetSinoVnState | null, formData: FormData): Promise<IGetSinoVnState> {
    const kanji = formData.get("kanji")?.toString().trim() || "";
    const customValue = formData.get("custom-value")?.toString() || "";

    const state: IGetSinoVnState = {
        kanji: {
            value: kanji,
            errorMessage: '',
            isError: false
        },
        dividerType,
        customValue,
        sinoVn: ""
    }

    if (kanji === "") {
        state.kanji.isError = true;
        state.kanji.errorMessage = "Kanji cannot be blank!"
    }

    // const result = kanji
    //     .split(/\r?\n/)
    //     .map(s => s.trim().toLowerCase())
    //     .join("\n");
    // state.sinoVn = result;
    if (!state.kanji.isError) {
        const response = await sendRequest<ApiResponse<string>>({
            url: '/sino-vietnamese',
            headers: {
                "Content-Type": "application/json"
            },
            method: 'POST',
            body: {
                kanjiArrayRaw: kanji,
                divider: dividerType === "custom" ? customValue : dividerType
            }
        });

        if (response.statusCode === 200) {
            state.sinoVn = response.data;
        }
    }
    return state;
}