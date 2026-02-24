import React, { useTransition } from "react";
import BasicButton from "@/components/ui/buttons/basic.button";
import ExportIcon from "@/components/ui/icons/export.icon";
import { useTranslations } from "next-intl";
import { FolderResponse } from "@/types/api/responses/common.response";
import { exportAllGrammarsInFolder } from "@/services/grammar.service";

const ExportGrammarsButton = ({ folder }: { folder: FolderResponse }) => {
    const t = useTranslations();
    const [isPending, startTransition] = useTransition();

    const handleExportGrammars = async () => {
        startTransition(async () => {
            await exportAllGrammarsInFolder(folder.id);
        });
    };

    return (
        <BasicButton
            width={55}
            icon={<ExportIcon />}
            text={t("Common.export")}
            onClick={handleExportGrammars}
        />
    );
};

export default ExportGrammarsButton;
