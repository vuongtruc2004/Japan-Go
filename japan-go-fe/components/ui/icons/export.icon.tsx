const ExportIcon = ({ size }: { size?: number }) => {
    return (
        <svg
            viewBox="0 0 24 24"
            role="presentation"
            xmlns="http://www.w3.org/2000/svg"
            width={size || 24}
            height={size || 24}
            fill="currentColor"
            className="shrink-0"
        >
            <path d="M5 12V5h14v7c0 .5.5 1 1 1s1-.5 1-1V5.25A2.25 2.25 0 0 0 18.75 3H5.25A2.25 2.25 0 0 0 3 5.25V12c0 .5.5 1 1 1s1-.5 1-1Z" />
            <path
                fillRule="evenodd"
                clipRule="evenodd"
                d="M12.796 20.67c-.44.44-1.152.44-1.591 0L7.83 17.296a1.125 1.125 0 0 1 1.59-1.59L11 17.159V11c0-.5.447-1 1-1 .553 0 1 .5 1 1v6.159l1.58-1.454a1.125 1.125 0 0 1 1.59 1.59l-3.375 3.375Z"
            />
        </svg>
    );
};

export default ExportIcon;
