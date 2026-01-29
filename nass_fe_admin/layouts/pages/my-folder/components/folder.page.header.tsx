import { useTranslations } from "next-intl";
import FolderCreateButton from "./folder.create.button";

const FolderPageHeader = () => {
    const t = useTranslations("Pages.folder");
    return (
        <div className="flex items-center justify-between">
            <h1 className="text-tc-primary mb-3 font-semibold">{t("title")}</h1>
            <FolderCreateButton />
        </div>
    );
};

export default FolderPageHeader;
