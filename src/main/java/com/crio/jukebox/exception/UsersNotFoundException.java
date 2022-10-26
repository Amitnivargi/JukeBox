package com.crio.jukebox.exception;

public class UsersNotFoundException extends RuntimeException{
    
         public UsersNotFoundException(){super();}
         public UsersNotFoundException(String msg){super(msg);}
}
