import React from "react";
import { KanjiResponse } from "@/types/api/responses/kanji.response";
import CreateSinoVietnameseButton from "@/features/kanji-details/components/create.sino.vietnamese.button";
import { Button } from "@mui/material";
import DeleteOutlinedIcon from "@mui/icons-material/DeleteOutlined";
import { deleteSinoVietnamese } from "@/services/kanji.service";
import { useRouter } from "next/navigation";
import { useTranslations } from "next-intl";

const SinoVietnameseList = ({ kanji }: { kanji: KanjiResponse }) => {
    const { refresh } = useRouter();
    const t = useTranslations();

    const handleDelete = async (sinoVietnameseId: number) => {
        await deleteSinoVietnamese(sinoVietnameseId);
        refresh();
    };

    return (
        <div className="space-y-4">
            <div className="flex items-center justify-between">
                <h2 className="text-base font-semibold">
                    {t("Pages.kanjiDetails.createSinoVietnamese")}
                </h2>
                <CreateSinoVietnameseButton kanji={kanji} />
            </div>

            <ul className="border-bdc-primary divide-bdc-primary overflow-hidden rounded-xl border divide-y">
                {kanji.sinoVietnameseList.map((sino) => (
                    <li
                        key={sino.id}
                        className="flex items-center justify-between px-4 py-3"
                    >
                        <span className="text-base">{sino.readingText}</span>
                        <Button
                            variant="text"
                            color="error"
                            sx={{
                                width: "32px",
                                minWidth: "32px",
                                height: "32px",
                                borderRadius: "50%",
                                "&:hover": {
                                    backgroundColor: "rgba(211, 47, 47, 0.1)",
                                },
                            }}
                            size="small"
                            onClick={() => handleDelete(sino.id)}
                        >
                            <DeleteOutlinedIcon fontSize="small" />
                        </Button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SinoVietnameseList;
