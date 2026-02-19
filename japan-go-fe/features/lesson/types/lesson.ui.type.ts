import React, { Dispatch, SetStateAction } from "react";
import {
    HideVocabularyLabelKey,
    LessonDetailsMoreButtonTextKey,
    ShowVocabularyLabelKey,
} from "@/types/app/i18n.type";

export interface ILessonDetailsMoreButtonItem {
    readonly id: string;
    icon: React.ReactNode;
    textKey: LessonDetailsMoreButtonTextKey;
}

export interface IVocabularyVisibilityToggleConfig {
    readonly id: string;
    visible: boolean;
    setVisible: Dispatch<SetStateAction<boolean>>;
    showLabelKey: ShowVocabularyLabelKey;
    hideLabelKey: HideVocabularyLabelKey;
    showClassName: string;
}
