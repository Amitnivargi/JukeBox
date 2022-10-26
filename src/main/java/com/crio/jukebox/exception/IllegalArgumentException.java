package com.crio.jukebox.exception;

public class IllegalArgumentException extends RuntimeException {

           public IllegalArgumentException(){
               super();
           }

           public IllegalArgumentException(String msg){
              super(msg);
           }
    
}
