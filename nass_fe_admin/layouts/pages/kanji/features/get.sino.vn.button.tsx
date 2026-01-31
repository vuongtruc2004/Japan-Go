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
import { IGetSinoVnState } from "../models/kanji.type";
import SinoVnResultTextArea from "./sino.vn.result.textarea";

const initialState: IGetSinoVnState | null = null;

const GetSinoVnButton = () => {
    const t = useTranslations("Pages");

    const [open, setOpen] = useState(false);
    const [dividerType, setDividerType] = useState<
        "line" | "whitespace" | "custom"
    >("line");

    const getSinoVnWithDividerType = getSinoVn.bind(null, dividerType);
    const [state, formAction, pending] = useActionState(
        getSinoVnWithDividerType,
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
                <p className="ml-1.5 text-sm">
                    {t("kanji.explore.enrichSinoVietnamese")}
                </p>
            </Button>

            <Modal open={open}>
                <div className="bg-bgc-page absolute top-1/2 left-1/2 w-[800px] -translate-x-1/2 -translate-y-1/2 rounded-md p-5">
                    <h1 className="text-tc-primary font-semibold">
                        {t("kanji.sinoVietnameseImport.title")}
                    </h1>

                    <form action={formAction} className="my-3">
                        <div className="grid w-full grid-cols-[2fr_1fr] items-start gap-x-5">
                            <TextField
                                defaultValue={state && state.kanji.value}
                                name="kanji"
                                multiline
                                rows={20}
                                placeholder={t(
                                    "kanji.sinoVietnameseImport.inputPlaceholder",
                                )}
                                error={state != null && state.kanji.isError}
                                helperText={
                                    <span className="text-tc-error">
                                        {state && state.kanji.errorMessage}
                                    </span>
                                }
                            />

                            <SinoVnResultTextArea
                                sinoVn={state?.sinoVn || ""}
                            />
                        </div>

                        <h1 className="text-tc-primary font-semibold">
                            {t("kanji.sinoVietnameseImport.delimiter")}
                        </h1>

                        <RadioGroup
                            value={dividerType}
                            onChange={(e) =>
                                setDividerType(
                                    e.target.value as
                                        | "line"
                                        | "whitespace"
                                        | "custom",
                                )
                            }
                            name="divider-type"
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
                                label={t(
                                    "kanji.sinoVietnameseImport.lineLabel",
                                )}
                            />
                            <FormControlLabel
                                value="whitespace"
                                control={<Radio />}
                                label={t(
                                    "kanji.sinoVietnameseImport.spaceLabel",
                                )}
                            />
                            <FormControlLabel
                                value="custom"
                                control={<Radio />}
                                label={
                                    <TextField
                                        name="custom-value"
                                        size="small"
                                        placeholder={t(
                                            "kanji.sinoVietnameseImport.customPlaceholder",
                                        )}
                                        defaultValue={state?.customValue}
                                    />
                                }
                            />
                        </RadioGroup>

                        <div className="mt-5 flex items-center justify-end gap-x-3">
                            <Button
                                disabled={pending}
                                onClick={() => setOpen(false)}
                                sx={{ columnGap: "8px" }}
                                variant="outlined"
                                color="error"
                            >
                                <CloseIcon fontSize="small" />
                                {t("kanji.sinoVietnameseImport.cancel")}
                            </Button>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                loading={pending}
                            >
                                {t("kanji.sinoVietnameseImport.confirm")}
                            </Button>
                        </div>
                    </form>
                </div>
            </Modal>
        </>
    );
};

export default GetSinoVnButton;
