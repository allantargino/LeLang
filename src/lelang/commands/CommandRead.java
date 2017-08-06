package lelang.commands;

import lelang.models.*;

public class CommandRead extends Command {

    private Variable _variable;

    public void SetVariable(Variable variable) {
        this._variable = variable;
    }

    public String WriteCode() {
        if (_variable != null) {
            String line = "scanf(\"";
            switch (_variable.GetType()) {
            case Variable.INTEGER:
                line += "%d";
                break;
            case Variable.DECIMAL:
                line += "%f";
                break;
            case Variable.STRING:
                line += "%s";
                break;
            case Variable.BOOLEAN:
                line += "%d";
                break;
            default:
                break;
            }
            line += "\", ";
            if(_variable.GetType()!=Variable.STRING)
                line += "&";
            line += _variable.GetId();
            line += ");\n";
            return line;
        } else {
            throw new RuntimeException("Variable value must be set");
        }
    }
}