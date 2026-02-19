import React, { useState } from "react";
import MoreHorizOutlinedIcon from "@mui/icons-material/MoreHorizOutlined";
import { TooltipCustom } from "@/components/ui/mui-custom/tooltip.custom";
import { useTranslations } from "next-intl";
import IconButtonCustom from "@/components/ui/mui-custom/icon.button.custom";
import { Divider, Popover } from "@mui/material";
import { useFolderDetails } from "@/features/your-library/contexts/folder.details";
import { deleteFolder } from "@/services/folder.service";
import { useRouter } from "@/i18n/navigation";
import BasicButton from "@/components/ui/buttons/basic.button";
import PushPinOutlinedIcon from "@mui/icons-material/PushPinOutlined";
import IosShareOutlinedIcon from "@mui/icons-material/IosShareOutlined";
import EditOutlinedIcon from "@mui/icons-material/EditOutlined";
import DeleteOutlineOutlinedIcon from "@mui/icons-material/DeleteOutlineOutlined";

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
                <div className="flex flex-col">
                    <BasicButton
                        width={50}
                        icon={<EditOutlinedIcon />}
                        text={t("Common.edit")}
                    />

                    <BasicButton
                        width={50}
                        icon={<IosShareOutlinedIcon />}
                        text={t("Common.share")}
                    />

                    <BasicButton
                        width={50}
                        icon={<PushPinOutlinedIcon />}
                        text={t("Common.pinToSidebar")}
                    />

                    <Divider />

                    <BasicButton
                        width={50}
                        icon={<DeleteOutlineOutlinedIcon />}
                        text={t("Pages.yourLibrary.folder.deleteFolder")}
                        onClick={handleDeleteFolder}
                        textColor="text-tc-error"
                    />
                </div>
            </Popover>
        </>
    );
};

export default FolderMoreButton;
