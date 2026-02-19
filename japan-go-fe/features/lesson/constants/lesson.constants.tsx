import { ILessonDetailsMoreButtonItem } from "@/features/lesson/types/lesson.ui.type";
import ModeEditOutlineOutlinedIcon from "@mui/icons-material/ModeEditOutlineOutlined";
import SchoolOutlinedIcon from "@mui/icons-material/SchoolOutlined";
import ExportIcon from "@/components/ui/icons/export.icon";

export const STROKE_COLORS = [
    "#e11d48",
    "#f97316",
    "#f59e0b",
    "#84cc16",
    "#22c55e",
    "#06b6d4",
    "#3b82f6",
    "#6366f1",
    "#a855f7",
    "#ec4899",
    "#14b8a6",
    "#8b5cf6",
    "#0ea5e9",
    "#10b981",
    "#ef4444",
];

export const LESSON_DETAILS_MORE_BUTTON_ITEMS: ILessonDetailsMoreButtonItem[] =
    [
        {
            id: "item-1",
            icon: <ModeEditOutlineOutlinedIcon />,
            textKey: "edit",
        },
        {
            id: "item-2",
            icon: <SchoolOutlinedIcon />,
            textKey: "addToClass",
        },
        {
            id: "item-3",
            icon: <ExportIcon size={20} />,
            textKey: "export",
        },
    ];
