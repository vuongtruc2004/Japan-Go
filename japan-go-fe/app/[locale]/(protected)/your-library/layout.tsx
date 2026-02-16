import LibraryTabs from "@/features/your-library/components/library.tabs";

const YourLibraryLayout = ({ tab }: { tab: React.ReactNode }) => {
    return (
        <div className="flex flex-col gap-y-5">
            <LibraryTabs />
            {tab}
        </div>
    );
};

export default YourLibraryLayout;
