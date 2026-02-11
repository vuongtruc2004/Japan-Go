import SwapVertIcon from "@mui/icons-material/SwapVert";
import { Button } from "@mui/material";
import GetSinoVietnameseButton from "./get.sino.vietnamese.button";
import TabsSwitchButtons from "./tabs.switch.buttons";

const KanjiToolsBox = () => {
    return (
        <div className="flex items-start justify-between">
            <TabsSwitchButtons />
            <div className="border-bdc-primary flex flex-col items-end gap-y-3 rounded-md border p-3">
                <GetSinoVietnameseButton />
                <Button
                    variant="outlined"
                    color="success"
                    sx={{ width: "max-content" }}
                >
                    <SwapVertIcon />
                    Sắp xếp
                </Button>
            </div>
        </div>
    );
};

export default KanjiToolsBox;

