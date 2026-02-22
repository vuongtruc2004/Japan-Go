export const isMarkdownFile = (file: File): boolean => {
    const isMarkdownExtension = file.name.toLowerCase().endsWith(".md");
    const isMarkdownMime = file.type === "text/markdown";

    return isMarkdownExtension || isMarkdownMime;
};
