package com.nass.business.grammar.constant;

import java.util.regex.Pattern;

public class GrammarPattern {

    // CHỈ chấp nhận đúng 1 dấu #
    public static final Pattern LESSON_HEADER =
            Pattern.compile("^\\s*#(?!#)\\s*(.+?)\\s*$");

    // CHỈ chấp nhận đúng 2 dấu ##
    public static final Pattern GRAMMAR_HEADER =
            Pattern.compile("^\\s*##(?!#)\\s*(.+?)\\s*$");

    // CHỈ chấp nhận đúng 3 dấu ###
    public static final Pattern MEANING_HEADER =
            Pattern.compile("^\\s*###(?!#)\\s*Ý\\s*nghĩa\\s*:?\\s*$",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);

    public static final Pattern STRUCTURE_HEADER =
            Pattern.compile("^\\s*###(?!#)\\s*Cấu\\s*trúc\\s*:?\\s*$",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);

    public static final Pattern EXAMPLE_HEADER =
            Pattern.compile("^\\s*###(?!#)\\s*Ví\\s*dụ\\s*:?\\s*$",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);

    public static final Pattern ADDITIONAL_NOTE_HEADER =
            Pattern.compile("^\\s*###(?!#)\\s*Chú\\s*ý\\s*bổ\\s*sung\\s*:?\\s*$",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);

    public static final Pattern ORDERED_ITEM =
            Pattern.compile("^\\s*(\\d+)\\.\\s*(.+?)\\s*$");

    public static final Pattern ARROW_LINE =
            Pattern.compile("^\\s*(?:⇒|=>)\\s*(.+?)\\s*$");
}

