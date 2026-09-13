package org.japan.helper.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardHelper {
    private static final String SEPARATOR = "------------------------";

    public String buildKanjiBackSide(
            String a,
            String b,
            String c,
            String d,
            String e,
            String f
    ) {
        StringBuilder result = new StringBuilder();

        if (!a.isBlank() && !a.equals(c)) {
            if (!b.isBlank()) {
                result.append(a)
                        .append("【")
                        .append(b)
                        .append("】")
                        .append("\n")
                        .append(SEPARATOR)
                        .append("\n");
            } else {
                result.append(a)
                        .append("\n")
                        .append(SEPARATOR)
                        .append("\n");
            }
            result.append(c)
                    .append("\n");
        }

        result.append("⇒ ")
                .append(d);

        if (!e.isBlank()) {
            result.append("\n")
                    .append(SEPARATOR)
                    .append("\n")
                    .append(e);
        }

        if (!f.isBlank()) {
            result.append("\n")
                    .append(SEPARATOR)
                    .append("\n")
                    .append(f);
        }

        return result.toString();
    }

    public String buildHiraganaBackSide(
            String a,
            String b,
            String c,
            String d,
            String e,
            String f
    ) {
        StringBuilder result = new StringBuilder();

        if (!a.isBlank() && !a.equals(c)) {
            if (!b.isBlank()) {
                result.append(a)
                        .append("【")
                        .append(b)
                        .append("】")
                        .append("\n")
                        .append(SEPARATOR)
                        .append("\n");
            } else {
                result.append(a)
                        .append("\n")
                        .append(SEPARATOR)
                        .append("\n");
            }
        }

        result.append("⇒ ")
                .append(d);

        if (!e.isBlank()) {
            result.append("\n")
                    .append(SEPARATOR)
                    .append("\n")
                    .append(e);
        }

        if (!f.isBlank()) {
            result.append("\n")
                    .append(SEPARATOR)
                    .append("\n")
                    .append(f);
        }

        return result.toString();
    }

    public String buildSentenceBackSide(
            String aWord,
            String aSentence,
            String b,
            String cWord,
            String cSentence,
            String dWord,
            String dSentence,
            String e,
            String f
    ) {
        StringBuilder result = new StringBuilder();

        result.append(aSentence)
                .append("\n")
                .append(SEPARATOR)
                .append("\n")
                .append(cSentence)
                .append("\n")
                .append("⇒ ")
                .append(dSentence)
                .append("\n")
                .append(SEPARATOR)
                .append("\n");

        if (!aWord.isBlank() && !aWord.equals(cWord)) {
            if (!b.isBlank()) {
                result.append(aWord)
                        .append("【")
                        .append(b)
                        .append("】")
                        .append("\n")
                        .append(SEPARATOR)
                        .append("\n");
            } else {
                result.append(aWord)
                        .append("\n")
                        .append(SEPARATOR)
                        .append("\n");
            }
        }

        result.append(cWord)
                .append("\n")
                .append("⇒ ")
                .append(dWord);

        if (!e.isBlank()) {
            result.append("\n")
                    .append(SEPARATOR)
                    .append("\n")
                    .append(e);
        }

        if (!f.isBlank()) {
            result.append("\n")
                    .append(SEPARATOR)
                    .append("\n")
                    .append(f);
        }

        return result.toString();
    }

    public String get(String[] columns, int index) {
        if (index >= columns.length || columns[index] == null) {
            return "";
        }

        return columns[index]
                .replace("\"", "")
                .trim();
    }

    public String[] splitByParentheses(String input) {
        int start = input.lastIndexOf('（');
        int end = input.lastIndexOf('）');

        // Nếu không có （ ） thì tìm ( )
        if (start == -1 || end == -1 || start >= end) {
            start = input.lastIndexOf('(');
            end = input.lastIndexOf(')');
        }

        if (start == -1 || end == -1 || start >= end) {
            return new String[]{input, ""};
        }

        return new String[]{
                input.substring(0, start).trim(),
                input.substring(start + 1, end).trim()
        };
    }
}
