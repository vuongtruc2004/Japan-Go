import React, { useState } from "react";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useTranslations } from "next-intl";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { Button, Popover } from "@mui/material";
import RemoveIcon from "@mui/icons-material/Remove";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";
import { deleteFolder } from "@/services/folder.service";
import { useRouter } from "@/i18n/navigation";

const FolderMoreButton = () => {
    const t = useTranslations();
    const { folder } = useFolderDetails();
    const { push } = useRouter();

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    const handleDeleteFolder = async () => {
        await deleteFolder(folder.id);
        push("/your-library/folder");
    };

    return (
        <>
            <TooltipCustom title={t("Common.viewMore")}>
                <IconButtonCustom
                    onClick={(event) => setAnchorEl(event.currentTarget)}
                >
                    <MoreHorizOutlinedIcon />
                </IconButtonCustom>
            </TooltipCustom>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={() => setAnchorEl(null)}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <div>
                    <Button
                        variant="text"
                        color="error"
                        onClick={handleDeleteFolder}
                    >
                        <RemoveIcon fontSize="small" sx={{ mr: "4px" }} />
                        {t("Pages.yourLibrary.folder.deleteFolder")}
                    </Button>
                </div>
            </Popover>
        </>
    );
};

export default FolderMoreButton;
