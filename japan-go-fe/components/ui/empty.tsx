import { EmptyBoxIcon } from "./icons/empty.box.icon";

const Empty = ({ text }: { text: string }) => {
    return (
        <div className="flex w-full flex-col items-center justify-center gap-y-3 p-5">
            <EmptyBoxIcon />
            <p className="text-tc-muted text-sm font-semibold select-none">
                {text}
            </p>
        </div>
    );
};

export default Empty;

