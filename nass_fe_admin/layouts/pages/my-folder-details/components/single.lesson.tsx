import AddLessonToFolderButton from "../features/add.lesson.to.folder.button";

const SingleLesson = ({ lesson }: { lesson: LessonResponse }) => {
    return (
        <div className="flex items-center justify-between px-4 py-2">
            <div>
                <div>
                    <p>{lesson.lessonName}</p>
                </div>
            </div>
            <AddLessonToFolderButton lesson={lesson} />
        </div>
    );
};

export default SingleLesson;
