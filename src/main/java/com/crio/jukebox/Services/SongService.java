package com.crio.jukebox.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.Repositories.ISongRepository;
import com.crio.jukebox.entities.Song;

public class SongService implements ISongServcie {

    private final ISongRepository _iSongRepository;
    public SongService(ISongRepository iSongRepository)
    {
            _iSongRepository=iSongRepository;
    }
    @Override
    public void loadData(String fileName) throws IllegalAccessError {
       
          try{
                  BufferedReader filReader = new BufferedReader(new FileReader(fileName));
                  String line="";
                  while((line=filReader.readLine())!=null)
                  {
                                 String[] tokens =line.split(",");
                                 Song song = new Song(tokens[0],tokens[1],tokens[3],tokens[4],Arrays.asList(tokens[5].split("#")));
                                 _iSongRepository.save(song);      
                  }

                  filReader.close();

          }
          catch(IOException e)
          {
              e.printStackTrace();
          }
          
        
    }
    
}
