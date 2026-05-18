package org.japan.service.deck;

import org.japan.dto.request.deck.QuizletFormatRequest;
import org.japan.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CardService {
    private static final String SEPARATOR = "------------------------------";

    public String formatQuizletData(QuizletFormatRequest request) {
        String raw = request.raw();

        if (raw == null || raw.isBlank()) {
            return "";
        }

        if (!raw.contains("$")) {
            throw new BadRequestException(
                    "Invalid raw data. It should contain '$' character to separate cards.",
                    "Invalid raw data. It should contain '$' character to separate cards."
            );
        }

        return Arrays.stream(raw.split("\\$", -1))
                .filter(line -> !line.isBlank())
                .map(this::formatOneCard)
                .collect(java.util.stream.Collectors.joining());
    }

    private String formatOneCard(String line) {
        // Tham số -1 để giữ nguyên toàn bộ phần tử, kể cả empty string
        String[] columns = line.split("\t", -1);

        String a = get(columns, 0); // front side
        String b = get(columns, 1);
        String c = get(columns, 2);
        String d = get(columns, 3);
        String e = get(columns, 4);
        String f = get(columns, 5);

        return a + "#" + buildBackSide(a, b, c, d, e, f) + "$";
    }

    private String buildBackSide(
            String a,
            String b,
            String c,
            String d,
            String e,
            String f
    ) {
        StringBuilder result = new StringBuilder();

        if (!b.isBlank()) {
            result.append(a)
                    .append("：")
                    .append(b)
                    .append("\n")
                    .append(SEPARATOR)
                    .append("\n");
        }

        result.append(c)
                .append("\n")
                .append("⇒ ")
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

    private String get(String[] columns, int index) {
        if (index >= columns.length) {
            return "";
        }

        return clean(columns[index]);
    }

    private String clean(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\"", "")
                .trim();
    }
}
