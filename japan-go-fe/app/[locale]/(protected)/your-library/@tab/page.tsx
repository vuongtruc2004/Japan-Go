import Empty from "@/components/ui/empty";
import { Button } from "@mui/material";

import { getTranslations } from "next-intl/server";

export async function generateMetadata({
    params,
}: {
    params: { locale: string };
}): Promise<{
    title: string;
}> {
    const { locale } = await params;
    const t = await getTranslations({ locale, namespace: "Common" });

    return {
        title: t("class.title"),
    };
}

const ClassPage = async () => {
    const t = await getTranslations("Pages.yourLibrary");

    return (
        <div>
            <div className="flex flex-col items-center">
                <Empty text={t("class.noClasses")} />
                <Button variant="contained" color="primary">
                    {t("class.createClass")}
                </Button>
            </div>
        </div>
    );
};

export default ClassPage;
