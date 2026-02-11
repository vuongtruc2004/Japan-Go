import Empty from "@/components/ui/empty";
import { Button } from "@mui/material";
import { useTranslations } from "next-intl";

const FolderEmpty = () => {
    const t = useTranslations("Pages.yourLibrary");
    return (
        <div className="flex flex-col items-center">
            <Empty text={t("folder.noFolders")} />
            <Button variant="contained" color="primary">
                {t("folder.createFolder")}
            </Button>
        </div>
    );
};

export default FolderEmpty;

