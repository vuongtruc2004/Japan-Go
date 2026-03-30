export function slugifyText(text: string): string {
    return text
        .toLowerCase()
        .trim()
        .replaceAll(/[/／]+/g, "・") // slash → ・
        .replaceAll(/\s+/g, "-"); // khoảng trắng → -
}
