const BrandStudyGuidesIcon = ({ size }: { size?: number }) => {
    return (
        <svg
            viewBox="0 0 24 24"
            role="presentation"
            xmlns="http://www.w3.org/2000/svg"
            width={size || 24}
            height={size || 24}
        >
            <path
                d="M5.333 5.333A2.667 2.667 0 0 1 8 2.667h16a2.667 2.667 0 0 1 2.667 2.666v21.334A2.667 2.667 0 0 1 24 29.333H8a2.667 2.667 0 0 1-2.667-2.666V5.333Z"
                fill="#E372FF"
            />
            <path
                d="m27.663 9.141-2.611 1a.468.468 0 0 0-.28.28l-1.015 2.6a.493.493 0 0 1-.922 0l-1.002-2.607a.491.491 0 0 0-.28-.286l-2.57-1.014a.494.494 0 0 1 0-.92l2.61-.993a.508.508 0 0 0 .287-.287l1.009-2.6a.5.5 0 0 1 .928 0l.995 2.614a.494.494 0 0 0 .287.28l2.604 1.013a.494.494 0 0 1-.04.92Z"
                fill="#4255FF"
            />
            <rect
                x="12"
                y="21.333"
                width="12"
                height="2.667"
                rx="1.333"
                fill="#fff"
            />
            <circle cx="9.333" cy="22.667" r="1.333" fill="#fff" />
            <rect
                x="12"
                y="14.667"
                width="12"
                height="2.667"
                rx="1.333"
                fill="#fff"
            />
            <circle cx="9.333" cy="16" r="1.333" fill="#fff" />
            <rect
                x="8"
                y="8"
                width="9.333"
                height="2.667"
                rx="1.333"
                fill="#fff"
            />
        </svg>
    );
};

export default BrandStudyGuidesIcon;
