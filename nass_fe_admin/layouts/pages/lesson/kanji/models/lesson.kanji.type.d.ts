import { Dispatch, SetStateAction } from "react";

export interface VisibilityToggleConfig {
    id: string;
    visible: boolean;
    setVisible: Dispatch<SetStateAction<boolean>>;
    showLabel: string;
    hideLabel: string;
}