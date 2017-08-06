package lelang.util;

public class Beautifier {

    private String _text;
    private String _indentation;
    private int _indentationlevel;

    public Beautifier(String text) {
        _text = text;
        _indentation = "";
        _indentationlevel = 0;
    }

    public String BeautifyCCode() {
        StringBuilder strBuilder = new StringBuilder();
        Boolean indentationFlag = false;

        String[] lines = _text.split("\n\r");
        for (String line : lines) {
            char[] chars = line.toCharArray();
            for (char c : chars) {
                switch (c) {
                case '{':
                    IncreaseLevel();
                    indentationFlag = false;
                    break;
                case '}':
                    DecreaseLevel();
                    indentationFlag = false;
                    break;
                default:
                    indentationFlag = true;
                    break;
                }
            }
            if (indentationFlag)
                strBuilder.append(_indentation);
            strBuilder.append(line).append("\n");
        }
        return strBuilder.toString();
    }

    private void IncreaseLevel() {
        _indentationlevel++;
        _indentation += "\t";

    }

    private void DecreaseLevel() {
        _indentationlevel--;
        _indentation.replaceFirst("\t", "");
    }
}