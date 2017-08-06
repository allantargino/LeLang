package lelang.commands;

import lelang.models.*;

import java.util.ArrayList;

public class CommandProgram extends CommandReceiver {
    ArrayList<Variable> varList;
    ArrayList<Command> comandos;

    public CommandProgram() {
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
        AppendLine("#include <stdio.h>");
        AppendLine("#include <stdbool.h>");
        
        AppendLine("int main()");
        AppendLine("{");
        //Variables
        for (Variable v : varList) {
            AppendLine(WriteVariable(v));
        }
        //Commands
        for (Command cmd : comandos) {
            AppendLine(cmd.WriteCode());
        }
        AppendLine("return 0;");
        AppendLine("}");

        return GetCommandCode();
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

        line += ";";
        return line;
    }
}