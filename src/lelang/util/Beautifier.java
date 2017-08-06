package lelang.util;

public class Beautifier {

    private String _text;
    private String _indentation;

    public Beautifier(String text) {
        _text = text;
        _indentation= "";
    }

     public String BeautifyCCode() {
        StringBuilder strBuilder = new StringBuilder();

        String[] lines = _text.split("\n");
        for (String line : lines) {
            if (!IsNullOrEmpty(line)) {
                String temp = _indentation;
                char[] chars = line.toCharArray();
                for (char c : chars) {
                    switch (c) {
                        case '{':
                            _indentation += "\t";
                            break;
                        case '}':
                            _indentation = _indentation.replaceFirst("\t", "");
                            temp = temp.replaceFirst("\t", "");
                            break;
                        default:
                            break;
                    }
                }
                strBuilder.append(temp).append(line).append("\n");
            }
        }
        return strBuilder.toString();
    }

    public static Boolean IsNullOrEmpty(String value) {
        if (value != null) {
            return value.length() == 0;
        } else {
            return true;
        }
    }
}