import { BaseResponse } from "./base.response";
import { LessonResponse } from "./lesson.response";

export interface FolderResponse extends BaseResponse<number> {
    folderName: string;
    parentId: number;
}

export interface FolderDetailsResponse extends FolderResponse {
    lessons: LessonResponse[];
}

export interface SentenceResponse extends BaseResponse<number> {
    japaneseRaw: string;
    vietnameseRaw: string;
    englishRaw: string;

    japaneseHtml: string;
    vietnameseHtml: string;
    englishHtml: string;
}