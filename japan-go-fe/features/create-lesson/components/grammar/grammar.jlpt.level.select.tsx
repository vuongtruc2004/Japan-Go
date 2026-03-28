import { useTranslations } from "next-intl";
import { GRAMMAR_JLPT_LEVELS } from "@/features/create-lesson/constants/create.lesson.constants";
import { IGrammarJlptLevel } from "@/features/create-lesson/types/create.lesson.ui.type";
import { getGrammarJlptLevelClassname } from "@/features/create-lesson/utils/create.lesson.utils";
import { useGrammarLessonCreate } from "@/features/create-lesson/contexts/grammar.lesson.create.provider";

const GrammarJlptLevelSelect = () => {
    const { selectedLevel, setSelectedLevel } = useGrammarLessonCreate();
    const t = useTranslations();

    const handleChangeSelectedLevel = (newSelectedLevel: IGrammarJlptLevel) => {
        setSelectedLevel(newSelectedLevel);
    };

    return (
        <div>
            <h1 className="mb-3 font-semibold">
                {t("Pages.createLesson.selectJlptLevel")}
            </h1>
            <div className="flex items-center gap-x-3">
                {GRAMMAR_JLPT_LEVELS.map((level) => {
                    const activeClass = getGrammarJlptLevelClassname(
                        selectedLevel,
                        level,
                    );

                    return (
                        <button
                            type="button"
                            key={level.id}
                            className={`${activeClass} bg-bgc-page flex h-10 w-max cursor-pointer items-center justify-center rounded-md border px-3 text-sm font-semibold transition-all duration-150`}
                            onClick={() => handleChangeSelectedLevel(level)}
                        >
                            {t(`Common.jlptLevels.${level.tabKeyTranslation}`)}
                        </button>
                    );
                })}
            </div>
        </div>
    );
};

export default GrammarJlptLevelSelect;
