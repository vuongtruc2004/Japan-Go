"use server";
import { DividerType } from "../types/kanji.enum";
import { IGetSinoVietnameseState } from "../types/kanji.state.type";
import { getSinoVietnameseOfKanji } from "@/services/kanji.service";

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

    if (!state.kanji.isError) {
        state.sinoVietnamese = await getSinoVietnameseOfKanji({
            kanjiArrayRaw: kanji,
            divider: dividerType === "custom" ? customValue : dividerType,
        });
    }
    return state;
}
