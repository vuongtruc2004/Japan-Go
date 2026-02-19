import React from "react";

type BasicButtonProps = {
    icon: React.ReactNode;
    text: string;
    textColor?: string;
    width?: number;
} & React.ButtonHTMLAttributes<HTMLButtonElement>;

const BasicButton: React.FC<BasicButtonProps> = ({
    icon,
    text,
    textColor,
    width,
    ...props
}) => {
    return (
        <button
            style={{
                width: `${(width || 62.5) * 4}px`,
                minWidth: `${(width || 62.5) * 4}px`,
            }}
            className={`hover:bg-hbgc-app ${textColor || ""} flex shrink-0 cursor-pointer items-center gap-x-3 px-3.5 py-3 transition-all duration-150`}
            {...props}
        >
            {icon}
            <p className="text-sm font-semibold whitespace-nowrap">{text}</p>
        </button>
    );
};

export default BasicButton;
