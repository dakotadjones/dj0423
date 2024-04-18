package src.main.java.tools;

import src.main.java.tools.Impl.Chainsaw;
import src.main.java.tools.Impl.Jackhammer;
import src.main.java.tools.Impl.Ladder;

public class ToolFactory {
    public static Tool createTool(String code) {
        switch (code.toUpperCase()) {
            case "CHNS":
                return new Chainsaw("CHNS", "stihl");
            case "LADW":
                return new Ladder("LADW", "werner");
            case "JAKD":
                return new Jackhammer("JAKD", "dewalt");
            case "JAKR":
                return new Jackhammer("JAKR", "ridgid");
            default:
                throw new IllegalArgumentException("Invalid tool code: " + code);
        }
    }
}
