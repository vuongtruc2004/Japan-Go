import React from "react";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";
import { useTranslations } from "next-intl";
import { Button, Popover } from "@mui/material";
import { COURSE_CREATE_LINK_ITEMS } from "@/features/your-library/constants/constants";
import { Link } from "@/i18n/navigation";

const CourseCreateButton = () => {
    const t = useTranslations();

    const [anchorEl, setAnchorEl] = React.useState<HTMLButtonElement | null>(
        null,
    );

    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div>
            <Button variant={"text"} color={"primary"} onClick={handleClick}>
                <AddOutlinedIcon fontSize="small" />
                <p className="font-semibold">{t("Common.course.create")}</p>
            </Button>

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "right",
                }}
                transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <div>
                    {COURSE_CREATE_LINK_ITEMS.map((item) => {
                        return (
                            <Link
                                key={item.id}
                                href={item.link}
                                className="hover:text-tc-highlight flex items-center gap-x-3 px-4 py-2"
                            >
                                {item.icon}
                                <p className="text-sm font-semibold">
                                    {t(item.nameKey)}
                                </p>
                            </Link>
                        );
                    })}
                </div>
            </Popover>
        </div>
    );
};

export default CourseCreateButton;
