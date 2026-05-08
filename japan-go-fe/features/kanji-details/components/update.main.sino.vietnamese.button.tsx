import React, { useState } from "react";
import { KanjiResponse } from "@/types/api/responses/kanji.response";
import { Button, MenuItem, Modal } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import EditOutlinedIcon from "@mui/icons-material/EditOutlined";
import { useTranslations } from "next-intl";
import { updateKanjiMainSinoVietnamese } from "@/services/kanji.service";
import { toast } from "react-toastify";
import { useRouter } from "next/navigation";

const UpdateMainSinoVietnameseButton = ({
    kanji,
}: {
    kanji: KanjiResponse;
}) => {
    const t = useTranslations();
    const { refresh } = useRouter();
    const [open, setOpen] = useState(false);
    const [updateSinoVietnameseId, setUpdateSinoVietnameseId] =
        useState<number>(kanji.sinoVietnameseList[0].id);

    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event: SelectChangeEvent<number>) => {
        setUpdateSinoVietnameseId(event.target.value);
    };

    const handleSubmit = async (e: React.SubmitEvent<HTMLFormElement>) => {
        e.preventDefault();
        await updateKanjiMainSinoVietnamese(kanji.id, updateSinoVietnameseId);
        toast.success(t("Pages.kanjiDetails.updateMainSinoVietnameseSuccess"), {
            hideProgressBar: true,
            closeOnClick: true,
            autoClose: 2000,
        });
        handleClose();
        refresh();
    };

    return (
        <>
            <Button
                variant="text"
                color="primary"
                sx={{
                    width: "32px",
                    minWidth: "32px",
                    height: "32px",
                    borderRadius: "50%",
                    position: "absolute",
                    top: "8px",
                    right: "8px",
                }}
                size="small"
                onClick={() => setOpen(true)}
            >
                <EditOutlinedIcon fontSize="small" />
            </Button>

            <Modal open={open} onClose={handleClose}>
                <div className="bg-bgc-app absolute top-1/2 left-1/2 w-150 -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <form onSubmit={handleSubmit}>
                        <Select
                            name="update-sino-vietnamese-id"
                            value={updateSinoVietnameseId}
                            onChange={handleChange}
                        >
                            {kanji.sinoVietnameseList.map((sino) => {
                                return (
                                    <MenuItem value={sino.id} key={sino.id}>
                                        {sino.readingText}
                                    </MenuItem>
                                );
                            })}
                        </Select>
                        <div className="mt-3 flex items-center justify-end gap-x-3">
                            <Button
                                onClick={handleClose}
                                sx={{ columnGap: "8px" }}
                                variant="outlined"
                                color="error"
                            >
                                <CloseIcon fontSize="small" />
                                {t("Common.cancel")}
                            </Button>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                            >
                                {t("Common.update")}
                            </Button>
                        </div>
                    </form>
                </div>
            </Modal>
        </>
    );
};

export default UpdateMainSinoVietnameseButton;
