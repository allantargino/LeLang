package lelang.models;

public class LeError {

    private ErrorCategory _category;
    private int _code;
    private int _line;
    private String _message;

    public LeError(ErrorCategory category, int code, int line, String message) {
        this._category = category;
        this._code = code;
        this._line = line;
        this._message = message;
    }

    public LeError(int code, int line, String message){
        this( ErrorCategory.Default,code,line,message); 
    }

    public ErrorCategory GetCategory() {
        return this._category;
    }

    public int GetCode() {
        return this._code;
    }

    public int GetLine() {
        return this._line;
    }

    public String GetMessage() {
        return this._message;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._category).append(","); //Category
        sb.append(this._code).append(","); //Code
        sb.append(this._message).append(","); //Message
        sb.append(this._line); //Line
        return sb.toString();
    }
}