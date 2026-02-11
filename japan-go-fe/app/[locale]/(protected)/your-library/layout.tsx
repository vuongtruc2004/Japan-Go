import LibraryTabs from "@/features/your-library/components/library.tabs";

const YourLibraryLayout = ({ tabs }: { tabs: React.ReactNode }) => {
    return (
        <div className="flex flex-col gap-y-5">
            <LibraryTabs />
            <div className="bg-bgc-app border-bdc-primary rounded-md border p-5">
                {tabs}
            </div>
        </div>
    );
};

export default YourLibraryLayout;
