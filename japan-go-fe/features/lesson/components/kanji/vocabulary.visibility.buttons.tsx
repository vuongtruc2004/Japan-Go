"use client";
import React from "react";
import { useVocabularyVisibilityToggles } from "@/features/lesson/hooks/use.vocabulary.visibility.toggles";
import { useTranslations } from "next-intl";
import Tooltip from "@mui/material/Tooltip";
import BrushIcon from "@mui/icons-material/Brush";
import TranslateIcon from "@mui/icons-material/Translate";
import AbcIcon from "@mui/icons-material/Abc";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import MenuBookIcon from "@mui/icons-material/MenuBook";
import CommentOutlinedIcon from "@mui/icons-material/CommentOutlined";
import ExtensionOutlinedIcon from "@mui/icons-material/ExtensionOutlined";

const VocabularyVisibilityButtons = () => {
    const t = useTranslations("Pages.lesson.kanji");
    const toggles = useVocabularyVisibilityToggles();

    const getToggleConfig = (id: string) => {
        switch (id) {
            case "stroke":
                return {
                    colorClass: "bg-[#9C27B0] hover:bg-[#7B1FA2] shadow-[#9C27B0]/20",
                    icon: <BrushIcon className="text-white" style={{ fontSize: "14px" }} />,
                    inactiveIcon: <BrushIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "14px" }} />
                };
            case "japanese":
                return {
                    colorClass: "bg-[#27C93F] hover:bg-[#1AAB29] shadow-[#27C93F]/20",
                    icon: <TranslateIcon className="text-white" style={{ fontSize: "14px" }} />,
                    inactiveIcon: <TranslateIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "14px" }} />
                };
            case "sino-vietnamese":
                return {
                    colorClass: "bg-[#25C6DA] hover:bg-[#00ACC1] shadow-[#25C6DA]/20",
                    icon: <AbcIcon className="text-white" style={{ fontSize: "16px" }} />,
                    inactiveIcon: <AbcIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "16px" }} />
                };
            case "reading":
                return {
                    colorClass: "bg-[#FFBD2E] hover:bg-[#DEA123] shadow-[#FFBD2E]/20",
                    icon: <VolumeUpIcon className="text-white" style={{ fontSize: "14px" }} />,
                    inactiveIcon: <VolumeUpIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "14px" }} />
                };
            case "meaning":
                return {
                    colorClass: "bg-[#FF8B3D] hover:bg-[#E67E22] shadow-[#FF8B3D]/20",
                    icon: <MenuBookIcon className="text-white" style={{ fontSize: "14px" }} />,
                    inactiveIcon: <MenuBookIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "14px" }} />
                };
            case "note":
                return {
                    colorClass: "bg-[#FF5F56] hover:bg-[#E0443E] shadow-[#FF5F56]/20",
                    icon: <CommentOutlinedIcon className="text-white" style={{ fontSize: "14px" }} />,
                    inactiveIcon: <CommentOutlinedIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "14px" }} />
                };
            case "supportVocabulary":
                return {
                    colorClass: "bg-[#E91E63] hover:bg-[#C2185B] shadow-[#E91E63]/20",
                    icon: <ExtensionOutlinedIcon className="text-white" style={{ fontSize: "14px" }} />,
                    inactiveIcon: <ExtensionOutlinedIcon className="text-neutral-400 dark:text-zinc-500" style={{ fontSize: "14px" }} />
                };
            default:
                return {
                    colorClass: "bg-gray-500 hover:bg-gray-600 shadow-gray-500/20",
                    icon: null,
                    inactiveIcon: null
                };
        }
    };

    return (
        <div className="flex items-center gap-x-2 bg-bgc-app border border-bdc-primary rounded-full px-4 py-2 w-max shadow-sm">
            {toggles.map((toggle) => {
                const config = getToggleConfig(toggle.id);
                const active = toggle.visible;
                const tooltipText = active
                    ? t(`hideVocabularyLabels.${toggle.hideLabelKey}`)
                    : t(`showVocabularyLabels.${toggle.showLabelKey}`);

                return (
                    <Tooltip key={toggle.id} title={tooltipText} arrow placement="top">
                        <button
                            onClick={() => toggle.setVisible((prev) => !prev)}
                            className={`w-7 h-7 rounded-full flex items-center justify-center transition-all duration-200 cursor-pointer shadow-sm border border-black/5 hover:scale-105 active:scale-95 ${
                                active
                                    ? `${config.colorClass} shadow-md`
                                    : "bg-neutral-100 dark:bg-zinc-800 text-neutral-400 hover:bg-neutral-200 dark:hover:bg-zinc-700"
                            }`}
                        >
                            {active ? config.icon : config.inactiveIcon}
                        </button>
                    </Tooltip>
                );
            })}
        </div>
    );
};

export default VocabularyVisibilityButtons;
