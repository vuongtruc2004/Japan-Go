"use client";
import React from "react";
import CreateKanjiLessonForm from "@/features/create-lesson/components/kanji/create.kanji.lesson.form";
import { BookResponse } from "@/types/api/responses/lesson.response";

const CreateKanjiLesson = ({ books }: { books: BookResponse[] }) => {
    return (
        <div className="flex flex-col gap-y-5">
            <CreateKanjiLessonForm books={books} />
        </div>
    );
};

export default CreateKanjiLesson;
