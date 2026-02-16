import { FolderResponse } from "@/types/api/responses/common.response";
import { ValidateField } from "@/types/app/share.type";

export interface IFolderCreateState {
    folderName: ValidateField;
    response?: FolderResponse;
}
