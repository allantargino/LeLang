package lelang.models;

import java.util.*;

import lelang.models.ErrorCategory;

public class ErrorHandler {

    private ArrayList<LeError> _errorList;

    public ErrorHandler() {
        _errorList = new ArrayList<LeError>();
    }

    public void AddError(ErrorCategory category, int code, int line, String message) {
        LeError e = new LeError(category, code, line, message);
        _errorList.add(e);
    }

    public Boolean HasError() {
        return _errorList.size() > 0;
    }

    public ArrayList<LeError> GetErrors() {
        return _errorList;
    }

    public String toString() {
        if (HasError()) {
            StringBuilder sb = new StringBuilder();
            for (LeError e : _errorList) {
                sb.append(e.toString()).append("\n");
            }
            return sb.toString();
        }

        else
            return "There is no error.";
    }
}