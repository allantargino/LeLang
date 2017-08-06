package lelang.models;

public class LeError{

    //Error Codes:
    private static final String LE0001 = "";
    private static final String LE0002 = "";
    private static final String LE0003 = "";
    private static final String LE0004 = "";

    private int _code;
    private int _line;
    private String _message;

    public LeError(int code, int line, String message){
        this._code = code;
        this._line = line;
        this._message = message;
    }

    public int GetCode(){
        return this._code;
    }

    public int GetLine(){
        return this._line;
    }

    public String GetMessage(){
        return this._message;
    }

    public String toString(){
        return "LE_" + this._code + ": " +  this._message + ", in line " + this._line;
    }

}