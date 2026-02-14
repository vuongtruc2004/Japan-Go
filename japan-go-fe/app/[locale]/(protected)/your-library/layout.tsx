import LibraryTabs from "@/features/your-library/components/library.tabs";

const YourLibraryLayout = ({ tab }: { tab: React.ReactNode }) => {
    return (
        <div className="flex flex-col gap-y-5">
            <LibraryTabs />
            <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
                {tab}
            </div>
        </div>
    );
};

export default YourLibraryLayout;
