public class CommandAssign extends Command {

    private Variable _toVar;

    private String _expression;

    public void SetToVariable(Variable variable) {
        this._toVar = variable;
    }

    public void SetExpression(String expression){
        this._expression = expression;
    }

    public String WriteCode() {
        return _toVar.GetId() + " =" + _expression + ";\n";
    }
}