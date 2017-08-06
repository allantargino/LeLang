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
        StringBuilder str = new StringBuilder();
        str.append("if(").append(_logicalExpression).append("){\n");

        if (!_trueCommandList.isEmpty()) {
            for (Command c : _trueCommandList) {
                str.append(c.WriteCode());
            }
        }

        if (!_falseCommandList.isEmpty()) {
            str.append("}else{\n");
            for (Command c : _falseCommandList) {
                str.append(c.WriteCode());
            }
        }

        str.append("}\n");
        return str.toString();
    }
}