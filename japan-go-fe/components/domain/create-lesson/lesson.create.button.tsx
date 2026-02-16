import React, { ButtonHTMLAttributes, cloneElement, ReactElement } from "react";
import { useTranslations } from "next-intl";
import { Popover, PopoverOrigin } from "@mui/material";
import { COURSE_CREATE_LINK_ITEMS } from "@/features/your-library/constants/your.library.constants";
import { Link } from "@/i18n/navigation";
import { FolderResponse } from "@/types/api/responses/common.response";
import { slugifyText } from "@/utils/slugify.text";

const LessonCreateButton = ({
    buttonElement,
    anchorOrigin,
    transformOrigin,
    folder,
}: {
    buttonElement: ReactElement<ButtonHTMLAttributes<HTMLButtonElement>>;
    anchorOrigin?: PopoverOrigin;
    transformOrigin?: PopoverOrigin;
    folder?: FolderResponse;
}) => {
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
            {cloneElement(buttonElement, { onClick: handleClick })}

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={handleClose}
                anchorOrigin={
                    anchorOrigin
                        ? anchorOrigin
                        : {
                              vertical: "bottom",
                              horizontal: "right",
                          }
                }
                transformOrigin={
                    transformOrigin
                        ? transformOrigin
                        : {
                              vertical: "top",
                              horizontal: "right",
                          }
                }
            >
                <div>
                    {COURSE_CREATE_LINK_ITEMS.map((item) => {
                        return (
                            <Link
                                key={item.id}
                                href={
                                    folder
                                        ? {
                                              pathname: item.link,
                                              query: {
                                                  folder: slugifyText(
                                                      folder.folderName +
                                                          "-" +
                                                          folder.id,
                                                  ),
                                              },
                                          }
                                        : {
                                              pathname: item.link,
                                          }
                                }
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

export default LessonCreateButton;
