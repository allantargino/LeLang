package lelang.commands;

import java.util.ArrayList;

public class CommandWhile extends CommandReceiver {
    private ArrayList<Command> _commandList;
    private String _logicalExpression;

    public CommandWhile() {
        this._commandList = new ArrayList<Command>();
    }

    public void SetLogicalExpression(String expression) {
        this._logicalExpression = expression;
    }

    public void AddCommand(Command command) {
        this._commandList.add(command);
    }

    public String WriteCode() {
        StringBuilder str = new StringBuilder();
        str.append("while(").append(_logicalExpression).append("){\n");

        if (!_commandList.isEmpty()) {
            for (Command c : _commandList) {
                str.append("\t\t").append(c.WriteCode());
            }
        }

        str.append("\t}\n");
        return str.toString();
    }
}