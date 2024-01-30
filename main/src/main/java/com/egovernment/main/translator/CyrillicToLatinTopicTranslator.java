package com.egovernment.main.translator;

import java.util.HashMap;
import java.util.Map;

public class CyrillicToLatinTopicTranslator {

    private static final Map<Character, String> cyrillicToLatinMap = new HashMap<>();

    public static String transliterateBulgarianToEnglish(String textWithSpaces) {
        String text = textWithSpaces.replace(' ', '-');
        StringBuilder transliteratedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            transliteratedText.append(cyrillicToLatinMap.getOrDefault(ch, String.valueOf(ch)));
        }
        return transliteratedText.toString();
    }

    static {
        cyrillicToLatinMap.put('А', "A");
        cyrillicToLatinMap.put('а', "a");
        cyrillicToLatinMap.put('Б', "B");
        cyrillicToLatinMap.put('б', "b");
        cyrillicToLatinMap.put('В', "V");
        cyrillicToLatinMap.put('в', "v");
        cyrillicToLatinMap.put('Г', "G");
        cyrillicToLatinMap.put('г', "g");
        cyrillicToLatinMap.put('Д', "D");
        cyrillicToLatinMap.put('д', "d");
        cyrillicToLatinMap.put('Е', "E");
        cyrillicToLatinMap.put('е', "e");
        cyrillicToLatinMap.put('Ж', "Zh");
        cyrillicToLatinMap.put('ж', "zh");
        cyrillicToLatinMap.put('З', "Z");
        cyrillicToLatinMap.put('з', "z");
        cyrillicToLatinMap.put('И', "I");
        cyrillicToLatinMap.put('и', "i");
        cyrillicToLatinMap.put('Й', "Y");
        cyrillicToLatinMap.put('й', "y");
        cyrillicToLatinMap.put('К', "K");
        cyrillicToLatinMap.put('к', "k");
        cyrillicToLatinMap.put('Л', "L");
        cyrillicToLatinMap.put('л', "l");
        cyrillicToLatinMap.put('М', "M");
        cyrillicToLatinMap.put('м', "m");
        cyrillicToLatinMap.put('Н', "N");
        cyrillicToLatinMap.put('н', "n");
        cyrillicToLatinMap.put('О', "O");
        cyrillicToLatinMap.put('о', "o");
        cyrillicToLatinMap.put('П', "P");
        cyrillicToLatinMap.put('п', "p");
        cyrillicToLatinMap.put('Р', "R");
        cyrillicToLatinMap.put('р', "r");
        cyrillicToLatinMap.put('С', "S");
        cyrillicToLatinMap.put('с', "s");
        cyrillicToLatinMap.put('Т', "T");
        cyrillicToLatinMap.put('т', "t");
        cyrillicToLatinMap.put('У', "U");
        cyrillicToLatinMap.put('у', "u");
        cyrillicToLatinMap.put('Ф', "F");
        cyrillicToLatinMap.put('ф', "f");
        cyrillicToLatinMap.put('Х', "H");
        cyrillicToLatinMap.put('х', "h");
        cyrillicToLatinMap.put('Ц', "Ts");
        cyrillicToLatinMap.put('ц', "ts");
        cyrillicToLatinMap.put('Ч', "Ch");
        cyrillicToLatinMap.put('ч', "ch");
        cyrillicToLatinMap.put('Ш', "Sh");
        cyrillicToLatinMap.put('ш', "sh");
        cyrillicToLatinMap.put('Щ', "Sht");
        cyrillicToLatinMap.put('щ', "sht");
        cyrillicToLatinMap.put('Ъ', "U");
        cyrillicToLatinMap.put('ъ', "u");
        cyrillicToLatinMap.put('Ь', "Y");
        cyrillicToLatinMap.put('ь', "y");
        cyrillicToLatinMap.put('Ю', "Yu");
        cyrillicToLatinMap.put('ю', "yu");
        cyrillicToLatinMap.put('Я', "Ya");
        cyrillicToLatinMap.put('я', "ya");
    }


}
