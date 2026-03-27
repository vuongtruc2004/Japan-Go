import { ExportGrammarLessonRequest } from "@/types/api/requests/lesson.request";
import { sendRequest } from "@/lib/send.request";

export const exportGrammarLessonsToQuizletText = async (
    request: ExportGrammarLessonRequest,
) => {
    return await sendRequest<string>({
        url: "/grammar-lessons/export-quizlet",
        method: "POST",
        responseType: "text",
        body: request,
    });
};
