export function slugifyText(text: string): string {
    if (!text) return "";

    // 1. Convert Vietnamese accented characters to unsigned equivalents
    let result = text
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, ""); // removes standard combining accents

    // 2. Map custom Vietnamese characters not covered by standard NFD normalizer
    const map: Record<string, string> = {
        "đ": "d", "Đ": "d"
    };
    result = result.replace(/[đĐ]/g, (match) => map[match] || match);

    // 3. Lowercase and trim
    result = result.toLowerCase().trim();

    // 4. Replace slashes with dot
    result = result.replaceAll(/[/／]+/g, "・");

    // 5. Replace spaces with hyphen
    result = result.replaceAll(/\s+/g, "-");

    // 6. Keep only ASCII alphanumeric, hyphen, dot, and CJK (Japanese) characters
    // Hiragana: \u3040-\u309f, Katakana: \u30a0-\u30ff, Kanji: \u4e00-\u9faf
    result = result.replace(/[^a-z0-9\-・\u3040-\u309f\u30a0-\u30ff\u4e00-\u9faf]/g, "");

    // 7. Clean up consecutive hyphens
    result = result.replace(/-+/g, "-");

    return result;
}
