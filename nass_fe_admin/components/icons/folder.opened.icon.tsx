const FolderOpenedIcon = ({ size }: { size?: number }) => {
    return (
        <svg
            viewBox="0 0 24 24"
            role="presentation"
            xmlns="http://www.w3.org/2000/svg"
            width={size || 24}
            height={size || 24}
            fill='currentColor'
        >
            <path
                fillRule="evenodd"
                clipRule="evenodd"
                d="M5 3.5a2 2 0 0 0-2 2v13a2 2 0 0 0 2 2h11.681c.858 0 1.62-.55 1.89-1.367l2.325-7c.43-1.296-.53-2.633-1.89-2.633H18.5V8a2 2 0 0 0-2-2h-4.18L9.342 3.873A2 2 0 0 0 8.18 3.5H5Zm11.5 6V8h-4.18a2 2 0 0 1-1.162-.373L8.18 5.5H5v10.673l1.762-5.305A1.993 1.993 0 0 1 8.652 9.5H16.5Zm-10.172 9H16.68l2.325-7H8.652l-2.324 7Z"
            />
        </svg>
    )
}

export default FolderOpenedIcon