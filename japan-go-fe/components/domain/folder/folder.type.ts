import { FolderResponse } from "@/types/api/responses/common.response";
import { ValidateField } from "@/types/share.type";

export  interface IFolderCreateState {
    folderName: ValidateField;
    response?: FolderResponse;
}