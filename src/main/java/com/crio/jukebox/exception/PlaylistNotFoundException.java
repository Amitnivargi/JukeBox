package com.crio.jukebox.exception;

public class PlaylistNotFoundException extends RuntimeException {
    
       public PlaylistNotFoundException(){
            super();
       }

       public PlaylistNotFoundException(String msg){
            super(msg);
       }
}
