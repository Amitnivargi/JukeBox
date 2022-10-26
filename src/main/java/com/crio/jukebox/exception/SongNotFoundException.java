package com.crio.jukebox.exception;

public class SongNotFoundException extends RuntimeException {
    

          public SongNotFoundException(){
            super();
          }

          public SongNotFoundException(String msg){
              super(msg);
          }
}
