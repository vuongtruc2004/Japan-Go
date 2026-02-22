"use client";

import React from "react";
import SinglePinFolder from "@/features/pin-folder/components/single.pin.folder";
import { FolderResponse } from "@/types/api/responses/common.response";

const PinFolderList = ({ pinFolders }: { pinFolders: FolderResponse[] }) => {
    if (!pinFolders.length) return null;

    return (
        <div className="flex flex-col gap-y-3">
            {pinFolders.map((folder) => (
                <SinglePinFolder folder={folder} key={folder.id} />
            ))}
        </div>
    );
};

export default PinFolderList;
