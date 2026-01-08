import CloseIcon from "@mui/icons-material/Close";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import {
    Button,
    FormControlLabel,
    Modal,
    Radio,
    RadioGroup,
    TextField,
} from "@mui/material";
import { useTranslations } from "next-intl";
import { useActionState, useState } from "react";
import { getSinoVn } from "../actions/get.sino.vn.action";
import { IGetSinoVn } from "../models/kanji.type";

const initialState: IGetSinoVn | null = null;

const GetSinoVnButton = () => {
    const t = useTranslations("Pages.kanji.explore");

    const [open, setOpen] = useState(false);
    const [customDivider, setCustomDivider] = useState("");

    const getSinoVnWithCustomDivider = getSinoVn.bind(null, customDivider);
    const [state, formAction, pending] = useActionState(
        getSinoVnWithCustomDivider,
        initialState,
    );

    return (
        <>
            <Button
                variant="outlined"
                color="success"
                onClick={() => setOpen(true)}
            >
                <FileUploadOutlinedIcon fontSize="small" />
                <p className="ml-1.5 text-sm">{t("enrichSinoVietnamese")}</p>
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-[800px] -translate-x-1/2 -translate-y-1/2 p-5">
                    <h1 className="text-tc-primary font-semibold">
                        Chép và dán dữ liệu vào đây để lấy phiên âm Hán Việt của
                        các từ đó
                    </h1>

                    <form action={formAction}>
                        <div className="my-3 grid w-full grid-cols-[2fr_1fr] items-start gap-x-5">
                            <TextField
                                defaultValue={state && state.kanji.value}
                                name="kanji"
                                multiline
                                rows={20}
                                placeholder="Nhập dữ liệu của bạn ở đây..."
                                error={state != null && state.kanji.isError}
                                helperText={
                                    <span className="text-tc-error">
                                        {state && state.kanji.errorMessage}
                                    </span>
                                }
                            />

                            <TextField
                                defaultValue={state?.sinoVn}
                                fullWidth
                                multiline
                                rows={20}
                                placeholder="Kết quả sẽ hiển thị ở đây"
                                disabled
                            />
                        </div>

                        <h1 className="text-tc-primary font-semibold">
                            Ngăn cách giữa các từ bằng:
                        </h1>

                        <RadioGroup
                            name="divider-type"
                            defaultValue={"line"}
                            sx={{
                                display: "flex",
                                flexDirection: "row",
                                alignItems: "center",
                                justifyContent: "start",
                                columnGap: "12px",
                            }}
                        >
                            <FormControlLabel
                                value="line"
                                control={<Radio />}
                                label="Dòng"
                            />
                            <FormControlLabel
                                value="whitespace"
                                control={<Radio />}
                                label="Khoảng trắng"
                            />
                            <FormControlLabel
                                value="custom"
                                control={<Radio />}
                                label={
                                    <TextField
                                        size="small"
                                        placeholder="Tùy chỉnh"
                                        value={customDivider}
                                        onChange={(e) =>
                                            setCustomDivider(e.target.value)
                                        }
                                    />
                                }
                            />
                        </RadioGroup>

                        <div className="mt-5 flex items-center justify-end gap-x-3">
                            <Button
                                disabled={pending}
                                onClick={() => setOpen(false)}
                                sx={{ columnGap: "8px" }}
                                variant="contained"
                                color="secondary"
                            >
                                <CloseIcon fontSize="small" />
                                Hủy nhập
                            </Button>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                loading={pending}
                            >
                                Nhập
                            </Button>
                        </div>
                    </form>
                </div>
            </Modal>
        </>
    );
};

export default GetSinoVnButton;
