public class Variable{
    public static final int INTEGER = 1;
    public static final int DECIMAL = 2;
    public static final int STRING  = 3;
    public static final int BOOLEAN = 4;
    
    private String _id;
    private int    _type;
    
    public Variable(String id, int type){
        this._id   = id;
        this._type = type;
    }
    
    public Variable(){
        this("", -1);
    }
    
    public void SetId(String id){
        this._id = id;
    }
    public void Settype(int type){
        this._type = type;
    }
    public String GetId(){
        return this._id;
    }
    public int Gettype(){
        return this._type;
    }
    
    
    public String toString(){
        return "VAR: "+ _id+ " (" + _type + ")";
    }
}