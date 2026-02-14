import React from "react";
import { FolderDetailsResponse } from "@/types/api/responses/common.response";
import FolderDetailsHeader from "@/features/your-library/components/folder/folder.details.header";

const FolderDetails = ({ folder }: { folder: FolderDetailsResponse }) => {
    return (
        <div>
            <FolderDetailsHeader folder={folder} />
        </div>
    );
};

export default FolderDetails;
