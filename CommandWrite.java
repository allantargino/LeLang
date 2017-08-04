public class CommandWrite extends Command {
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_ID = 2;

    private String _content;
    private int _type;
    private Variable _variable;

    public CommandWrite(){
        _type = TYPE_TEXT;
    }

    public void SetContent(String content) {
        this._content = content;
    }

    public void SetType(int type) {
        this._type = type;
    }

    public void SetVariable(Variable variable) {
        this._variable = variable;
    }

    public String WriteCode() {
        if (_type == TYPE_TEXT) {
            return "printf(" + _content + ");\n";
        } else if (_type == TYPE_ID) {
            if (_variable != null) {
                String format = "";
                switch (_variable.GetType()) {
                case Variable.INTEGER:
                    format = "%d";
                    break;
                case Variable.DECIMAL:
                    format = "%.6f";
                    break;
                case Variable.STRING:
                    format = "%s";
                    break;
                case Variable.BOOLEAN:
                    format = "%d";
                    break;
                default:
                    break;
                }
                return "printf(\"" + format + "\", " + _variable.GetId() + ");\n";
            } else {
                throw new RuntimeException("Variable value must be set");
            }
        } else {
            throw new RuntimeException("Type not supported");
        }
    }
}