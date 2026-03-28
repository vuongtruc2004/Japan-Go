"use client";
import React, { SetStateAction } from "react";
import { BookResponse } from "@/types/api/responses/lesson.response";
import { MenuItem, Select } from "@mui/material";
import { useTranslations } from "next-intl";

const BookSelect = ({
    books,
    bookId,
    setBookId,
}: {
    books: BookResponse[];
    bookId: number;
    setBookId: React.Dispatch<SetStateAction<number>>;
}) => {
    const t = useTranslations();
    return (
        <div>
            <h1 className="mb-3 font-semibold">
                {t("Pages.createLesson.selectBook")}
            </h1>

            <Select
                size="small"
                value={bookId}
                onChange={(e) => setBookId(e.target.value)}
            >
                {books.map((book) => {
                    return (
                        <MenuItem value={book.id} key={book.id}>
                            {book.vietnameseTitle}
                        </MenuItem>
                    );
                })}
            </Select>
        </div>
    );
};

export default BookSelect;
