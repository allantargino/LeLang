package lelang.commands;

public abstract class Command {
    private StringBuilder _strBuilder;

    public Command() {
        _strBuilder = new StringBuilder();
    }

    protected void AppendLine(String text) {
        _strBuilder.append(text).append("\n\r");
    }

    protected String GetCommandCode() {
        return _strBuilder.toString();
    }

    public abstract String WriteCode();
}