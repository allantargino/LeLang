package lelang.models;

public class Variable
{
    public static final int BOOLEAN = 1;
    public static final int INTEGER = 2;
    public static final int DECIMAL = 3;
    public static final int STRING  = 4;

    
    private String _id;
    private int    _type;
    private Boolean _isConst;
    private String _expression;
    
    public Variable(String id, int type, Boolean isConst){
        this._id   = id;
        this._type = type;
        this._isConst = isConst;
    }

    public Variable(){
        this("", -1, false);
    }
    
    public void SetId(String id){
        this._id = id;
    }
    public void SetType(int type){
        this._type = type;
    }

    public void SetExpression(String expression){
        this._expression = expression;
    }

    public static int GetTypeNumber(String type){
        switch(type.toUpperCase()){
            case "INT":
                return INTEGER;
            case "DECIMAL":
                return DECIMAL;
            case "STR":
                return STRING;
            case "BOOL":
                return BOOLEAN;
            default:
                throw new RuntimeException("Variable not supported.");
        }
    }
    public String GetId(){
        return this._id;
    }
    public int GetType(){
        return this._type;
    }

    public Boolean IsConst(){
        return this._isConst;
    }

    public String GetExpression(){
        return this._expression;
    }
    
    
    public String toString(){
        return "VAR: "+ _id+ " (" + _type + ")";
    }
}