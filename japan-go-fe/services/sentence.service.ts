export const exportAllGrammarExampleSentencesInFolder = async (
    folderId: string | number,
) => {
    const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_URL}/sentences/grammar/export/${folderId}`,
    );
    const blob = await response.blob();

    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "grammars.xlsx";
    a.click();
};
