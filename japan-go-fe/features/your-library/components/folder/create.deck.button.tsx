import React from "react";
import { useTranslations } from "next-intl";
import { Button } from "@mui/material";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";
import { createDeckFromFolder } from "@/services/deck.service";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";

const CreateDeckButton = () => {
    const { folder } = useFolderDetails();
    const t = useTranslations();

    const handleClick = async () => {
        const response = await createDeckFromFolder({
            folderId: folder.id,
            title: folder.folderName + "_deck",
            description: "",
        });
        console.log(response);
    };

    return (
        <Button variant="contained" color="primary" onClick={handleClick}>
            <AddOutlinedIcon fontSize="small" />
            {t("Pages.yourLibrary.folder.createDeck")}
        </Button>
    );
};

export default CreateDeckButton;
