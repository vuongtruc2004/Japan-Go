import React, {
    ButtonHTMLAttributes,
    cloneElement,
    ReactElement,
    useState,
} from "react";
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
    onParentClose,
}: {
    buttonElement: ReactElement<ButtonHTMLAttributes<HTMLButtonElement>>;
    anchorOrigin?: PopoverOrigin;
    transformOrigin?: PopoverOrigin;
    folder?: FolderResponse;
    onParentClose?: () => void;
}) => {
    const t = useTranslations();

    const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

    return (
        <div>
            {cloneElement(buttonElement, {
                onClick: (event) => setAnchorEl(event.currentTarget),
            })}

            <Popover
                open={Boolean(anchorEl)}
                anchorEl={anchorEl}
                onClose={() => setAnchorEl(null)}
                anchorOrigin={
                    anchorOrigin ?? {
                        vertical: "bottom",
                        horizontal: "right",
                    }
                }
                transformOrigin={
                    transformOrigin ?? {
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
                                onClick={() => {
                                    setAnchorEl(null);
                                    onParentClose?.();
                                }}
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
