import { DecoratorClassName } from "@/types/enums/share.enum";
import { IGrammarJlptLevel } from "@/features/create-lesson/types/create.lesson.ui.type";

export function getGrammarJlptLevelClassname(
    activeTab: IGrammarJlptLevel,
    tab: IGrammarJlptLevel,
) {
    return activeTab === tab ? tab.colorStyle : DecoratorClassName.PRIMARY;
}
