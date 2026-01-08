import GetSinoVnButton from "../features/get.sino.vn.button";
import TabsSwitchButtons from "../features/tabs.switch.buttons";

const KanjiToolsBox = () => {
    return (
        <div className="flex items-start justify-between">
            <TabsSwitchButtons />
            <div className="border-bdc-primary flex flex-col gap-y-3 rounded-md border p-3">
                <GetSinoVnButton />
                <GetSinoVnButton />
            </div>
        </div>
    );
};

export default KanjiToolsBox;
