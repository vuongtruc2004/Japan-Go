'use server'

import { IGetSinoVn } from "../models/kanji.type";


export async function getSinoVn(customDivider: string, initialState: IGetSinoVn | null, formData: FormData): Promise<IGetSinoVn> {
    const kanji = formData.get("kanji")?.toString().trim() || "";
    const dividerType = formData.get("divider-type")?.toString() || "line";

    const result: IGetSinoVn = {
        kanji: {
            value: kanji
        },
        dividerType: dividerType,
        customDivider: {
            value: customDivider
        }
    }

    if (kanji === "") {
        result.kanji.isError = true;
        result.kanji.errorMessage = "Kanji cannot be blank!"
    }

    switch (dividerType) {
        case "line":
            break;
        case "whitespace":
            break;
        case "custom":
            if (customDivider.trim() === "") {
                result.customDivider.isError = true;
                result.customDivider.errorMessage = "Custom divider cannot blank!";
                break;
            }
            break;
        default:
    }

    return result;
}