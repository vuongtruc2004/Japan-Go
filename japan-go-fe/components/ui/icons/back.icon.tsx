const BackIcon = ({ size }: { size?: number }) => {
    return (
        <svg
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
            width={size || 24}
            height={size || 24}
            fill="none"
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth="1.8"
        >
            <path d="M10 6L6 10l4 4" />

            <path d="M6 10h7a5 5 0 1 1 0 10h-3" />
        </svg>
    );
};

export default BackIcon;
