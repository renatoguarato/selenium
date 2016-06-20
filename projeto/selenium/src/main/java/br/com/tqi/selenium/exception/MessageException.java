package br.com.tqi.selenium.exception;




public class MessageException extends Exception {

    private static final long serialVersionUID = -7003053437140853960L;


    public MessageException( String message, Throwable cause ) {

        super( message, cause );
    }


    public MessageException( String message ) {

        super( message );
    }


    public MessageException( Throwable cause ) {

        super( cause );
    }

}
