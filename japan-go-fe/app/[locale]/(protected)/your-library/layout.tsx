import LibraryTabs from "@/features/your-library/components/library.tabs";
import WrapBox from "@/components/ui/wrap.box";

const YourLibraryLayout = ({ tab }: { tab: React.ReactNode }) => {
    return (
        <div className="flex flex-col gap-y-5">
            <LibraryTabs />
            <WrapBox>{tab}</WrapBox>
        </div>
    );
};

export default YourLibraryLayout;
