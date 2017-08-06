package lelang.commands;

import java.util.ArrayList;

public class CommandIf extends CommandReceiver {
    private ArrayList<Command> _trueCommandList;
    private ArrayList<Command> _falseCommandList;
    private Boolean _elseFlag;
    private String _logicalExpression;

    public CommandIf() {
        this._trueCommandList = new ArrayList<Command>();
        this._falseCommandList = new ArrayList<Command>();
        this._elseFlag = false;
    }

    public void SetLogicalExpression(String expression) {
        this._logicalExpression = expression;
    }

    public void AddCommand(Command command) {
        if(_elseFlag==false)
            this._trueCommandList.add(command);
        else
            this._falseCommandList.add(command);
    }

    public void SetElseFlag(Boolean value){
        this._elseFlag = value;
    }

    public String WriteCode() {
        AppendLine("if(" + _logicalExpression + "){\n");
        if (!_trueCommandList.isEmpty()) {
            for (Command c : _trueCommandList) {
                AppendLine(c.WriteCode());
            }
        }

        if (!_falseCommandList.isEmpty()) {
            AppendLine("}else{\n");
            for (Command c : _falseCommandList) {
                AppendLine(c.WriteCode());
            }
        }

        AppendLine("}\n");
        return GetCommandCode();
    }
}