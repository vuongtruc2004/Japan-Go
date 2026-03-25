export function slugifyText(text: string): string {
    return text.toLowerCase().trim().replaceAll(/\s+/g, "-");
}
