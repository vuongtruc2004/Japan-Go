package org.japan.service.deck;

import lombok.RequiredArgsConstructor;
import org.japan.dto.request.deck.QuizletFormatRequest;
import org.japan.exception.BadRequestException;
import org.japan.helper.card.CardHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardHelper cardHelper;

    public String formatQuizletDataKanji(QuizletFormatRequest request) {
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
                .map(line -> {
                    // Tham số -1 để giữ nguyên toàn bộ phần tử, kể cả empty string
                    String[] columns = line.split("\t", -1);

                    String a = cardHelper.get(columns, 0); // front side
                    String b = cardHelper.get(columns, 1);
                    String c = cardHelper.get(columns, 2);
                    String d = cardHelper.get(columns, 3);
                    String e = cardHelper.get(columns, 4);
                    String f = cardHelper.get(columns, 5);

                    return a + "#" + cardHelper.buildKanjiBackSide(a, b, c, d, e, f) + "$";
                })
                .collect(Collectors.joining());
    }

    public String formatQuizletDataHiragana(QuizletFormatRequest request) {
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
                .map(line -> {
                    // Tham số -1 để giữ nguyên toàn bộ phần tử, kể cả empty string
                    String[] columns = line.split("\t", -1);

                    String a = cardHelper.get(columns, 0);
                    String b = cardHelper.get(columns, 1);
                    String c = cardHelper.get(columns, 2); // front side
                    String d = cardHelper.get(columns, 3);
                    String e = cardHelper.get(columns, 4);
                    String f = cardHelper.get(columns, 5);

                    return c + "#" + cardHelper.buildHiraganaBackSide(a, b, c, d, e, f) + "$";
                })
                .collect(Collectors.joining());
    }

    public String formatQuizletDataSentence(QuizletFormatRequest request) {
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
                .map(line -> {
                    // Tham số -1 để giữ nguyên toàn bộ phần tử, kể cả empty string
                    String[] columns = line.split("\t", -1);

                    String a = cardHelper.get(columns, 0);
                    String b = cardHelper.get(columns, 1);
                    String c = cardHelper.get(columns, 2);
                    String d = cardHelper.get(columns, 3);
                    String e = cardHelper.get(columns, 4);
                    String f = cardHelper.get(columns, 5);

                    String aWord = cardHelper.splitByParentheses(a)[0];
                    String aSentence = cardHelper.splitByParentheses(a)[1]; // frontside

                    String cWord = cardHelper.splitByParentheses(c)[0];
                    String cSentence = cardHelper.splitByParentheses(c)[1];

                    String dWord = cardHelper.splitByParentheses(d)[0];
                    String dSentence = cardHelper.splitByParentheses(d)[1];

                    String result;
                    if (!aSentence.isBlank() && !cSentence.isBlank()) {
                        result = aSentence + "#" + cardHelper.buildSentenceBackSide(aWord, aSentence, b, cWord, cSentence, dWord, dSentence, e, f) + "$";
                    } else {
                        result = c + "#" + cardHelper.buildHiraganaBackSide(a, b, c, d, e, f) + "$";
                    }

                    return result;
                })
                .collect(Collectors.joining());
    }

    public String formatQuizletTech(QuizletFormatRequest request) {
        String raw = request.raw();

        if (raw == null || raw.isBlank()) {
            return "";
        }

        if (!raw.contains("@@")) {
            throw new BadRequestException(
                    "Invalid raw data. It should contain '@@' character to separate cards.",
                    "Invalid raw data. It should contain '@@' character to separate cards."
            );
        }

        return Arrays.stream(raw.split("@@", -1))
                .filter(line -> !line.isBlank())
                .map(line -> {
                    // Tham số -1 để giữ nguyên toàn bộ phần tử, kể cả empty string
                    String[] columns = line.split("\t", -1);

                    String a = cardHelper.get(columns, 0);
                    String b = cardHelper.get(columns, 1);
                    String c = cardHelper.get(columns, 2);
                    String d = cardHelper.get(columns, 3);
                    String e = cardHelper.get(columns, 4);
                    String f = cardHelper.get(columns, 5);

                    String frontside = cardHelper.buildTechFrontSide(a, b, c, d, e);
                    return frontside + "##" + f + "@@";
                })
                .collect(Collectors.joining());
    }
}
