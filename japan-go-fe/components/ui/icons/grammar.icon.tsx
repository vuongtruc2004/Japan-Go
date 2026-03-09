const GrammarIcon = ({ size }: { size?: number }) => {
    return (
        <svg
            viewBox="0 0 24 24"
            role="presentation"
            xmlns="http://www.w3.org/2000/svg"
            width={size || 24}
            height={size || 24}
            fill="none"
            stroke="currentColor"
            strokeWidth="1.8"
            strokeLinecap="round"
            strokeLinejoin="round"
        >
            <rect x="7" y="3" width="14" height="18" rx="2" />
            <circle cx="5" cy="7" r="1.2" fill="currentColor" stroke="none" />
            <circle cx="5" cy="12" r="1.2" fill="currentColor" stroke="none" />
            <circle cx="5" cy="17" r="1.2" fill="currentColor" stroke="none" />
            <line x1="6.2" y1="7" x2="7" y2="7" />
            <line x1="6.2" y1="12" x2="7" y2="12" />
            <line x1="6.2" y1="17" x2="7" y2="17" />
            <text
                x="14"
                y="15"
                textAnchor="middle"
                fontSize="9"
                fontWeight="700"
                fill="currentColor"
                stroke="none"
                fontFamily="Noto Sans JP, sans-serif"
            >
                文
            </text>
        </svg>
    );
};

export default GrammarIcon;
