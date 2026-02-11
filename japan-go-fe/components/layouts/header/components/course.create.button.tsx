import { Link } from "@/i18n/navigation";
import StyleOutlinedIcon from "@mui/icons-material/StyleOutlined";
import { useTranslations } from "next-intl";

const CourseCreateButton = () => {
    const t = useTranslations("Common");
    return (
        <div>
            <Link
                href={"/your-library"}
                className="hover:bg-hbgc-app flex w-full min-w-62.5 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150"
            >
                <StyleOutlinedIcon />
                <p className="text-sm font-semibold whitespace-nowrap">
                    {t("course")}
                </p>
            </Link>
        </div>
    );
};

export default CourseCreateButton;

