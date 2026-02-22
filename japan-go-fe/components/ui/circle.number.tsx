import React from "react";

const CircleNumber = ({ num }: { num: number }) => {
    return (
        <span className="flex h-4.5 w-4.5 items-center justify-center rounded-full border text-xs">
            {num}
        </span>
    );
};

export default CircleNumber;
