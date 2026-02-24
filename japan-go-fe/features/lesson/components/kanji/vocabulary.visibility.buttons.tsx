"use client";
import React from "react";
import { useVocabularyVisibilityToggles } from "@/features/lesson/hooks/use.vocabulary.visibility.toggles";
import { useTranslations } from "next-intl";
import { DecoratorClassName } from "@/types/enums/share.enum";
import VisibilityOutlinedIcon from "@mui/icons-material/VisibilityOutlined";
import VisibilityOffOutlinedIcon from "@mui/icons-material/VisibilityOffOutlined";
import WrapBox from "@/components/ui/wrap.box";

const VocabularyVisibilityButtons = () => {
    const t = useTranslations("Pages.lesson.kanji");
    const toggles = useVocabularyVisibilityToggles();

    return (
        <WrapBox>
            <div className="flex items-center gap-x-3">
                {toggles.map((toggle) => {
                    return (
                        <button
                            key={toggle.id}
                            className={`select-none ${toggle.visible ? toggle.showClassName : DecoratorClassName.PRIMARY} flex cursor-pointer items-center gap-x-1.5 rounded-md border px-4 py-2 text-sm font-semibold transition-all duration-150`}
                            onClick={() => {
                                toggle.setVisible((prev) => !prev);
                            }}
                        >
                            {toggle.visible ? (
                                <VisibilityOutlinedIcon fontSize="small" />
                            ) : (
                                <VisibilityOffOutlinedIcon fontSize="small" />
                            )}
                            {toggle.visible
                                ? t(
                                      `hideVocabularyLabels.${toggle.hideLabelKey}`,
                                  )
                                : t(
                                      `showVocabularyLabels.${toggle.showLabelKey}`,
                                  )}
                        </button>
                    );
                })}
            </div>
        </WrapBox>
    );
};

export default VocabularyVisibilityButtons;
