public class Error{

    //Error Codes:
    private static final String LE0001 = "";

    private int _code;
    private String _message;

    public Error(int code, String message){
        this._code = code;
        this._message = message;
    }

    public int GetCode(){
        return this._code;
    }

    public String GetMessage(){
        return this._message;
    }

    public String toString(){
        return "LE_" + this._code + ": " +  this._message;
    }

}