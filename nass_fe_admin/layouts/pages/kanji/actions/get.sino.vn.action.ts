'use server'
import { IGetSinoVn } from "../models/kanji.type";


export async function getSinoVn(customDivider: string, initialState: IGetSinoVn | null, formData: FormData): Promise<IGetSinoVn> {
    const kanji = formData.get("kanji")?.toString().trim() || "";
    const dividerType = formData.get("divider-type")?.toString() || "line";

    const state: IGetSinoVn = {
        kanji: {
            value: kanji,
            errorMessage: '',
            isError: false
        },
        dividerType: dividerType,
        customDivider,
        sinoVn: ""
    }

    if (kanji === "") {
        state.kanji.isError = true;
        state.kanji.errorMessage = "Kanji cannot be blank!"
    }

    let divider;
    switch (dividerType) {
        case "whitespace":
            divider = "\\s+";
            break;
        case "custom":
            divider = customDivider;
            break;
        default:
            divider = "\n"
    }

    if (!state.kanji.isError) {
        const response = await fetch('http://localhost:2509/api/v1/kanji/sino-vn', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                kanjiRaw: kanji,
                divider
            })
        });
        const result: ApiResponse<string[]> = await response.json();
        if (result.statusCode === 200) {
            state.sinoVn = result.data.join(divider);
        }
    }
    return state;
}