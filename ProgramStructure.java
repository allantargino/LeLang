import java.util.ArrayList;

public class ProgramStructure {
    ArrayList<Variable> varList;
    ArrayList<Command> comandos;

    public ProgramStructure() {
        this.varList = new ArrayList<Variable>();
        this.comandos = new ArrayList<Command>();
    }

    public void AddVariable(Variable v) {
        this.varList.add(v);
    }

    public void AddCommand(Command cmd) {
        this.comandos.add(cmd);
    }

    public String WriteCode() {
        StringBuilder str = new StringBuilder();
        str.append("#include <stdio.h>\n");
        str.append("#include <stdbool.h>\n");
        str.append("int main()\n");
        str.append("{\n");
        //Variables
        for (Variable v : varList) {
            str.append("\t" + WriteVariable(v));
        }
        //Commands
        for (Command cmd : comandos) {
            str.append("\t" + cmd.WriteCode());
        }
        str.append("\treturn 0;\n");
        str.append("}");
        return str.toString();
    }

    private String WriteVariable(Variable v) {
        String line = "";

        switch (v.GetType()) {
        case Variable.BOOLEAN:
            line += "bool ";
            break;
        case Variable.INTEGER:
            line += "int ";
            break;
        case Variable.DECIMAL:
            line += "float ";
            break;
        case Variable.STRING:
            line += "char* ";
            break;
        }

        if (v.IsConst()) {
            line+= "const " + v.GetId()+ " =" + v.GetExpression();
        }else{
            line+= v.GetId();
        }

        line += ";\n";
        return line;
    }
}