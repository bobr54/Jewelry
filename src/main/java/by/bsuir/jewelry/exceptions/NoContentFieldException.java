package by.bsuir.jewelry.exceptions;

import javafx.scene.text.Text;

public class NoContentFieldException extends Exception{
    public NoContentFieldException() {
        super();
    }

    public NoContentFieldException(String message) {
        super(message);
    }
    public NoContentFieldException(String message, Text setText, Text deleteText) {
        setText.setOpacity(1);
        deleteText.setOpacity(0);
        System.out.println(message);
    }

    public NoContentFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContentFieldException(Throwable cause) {
        super(cause);
    }
}
