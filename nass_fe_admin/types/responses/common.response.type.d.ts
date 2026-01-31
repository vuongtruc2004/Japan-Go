interface FolderResponse extends BaseResponse<number> {
    folderName: string;
    parentId: number;
}

interface FolderDetailsResponse extends FolderResponse {
    lessons: LessonResponse[];
}

interface SentenceResponse extends BaseResponse<number> {
    japaneseRaw: string;
    vietnameseRaw: string;
    englishRaw: string;

    japaneseHtml: string;
    vietnameseHtml: string;
    englishHtml: string;
}