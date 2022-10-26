package com.crio.jukebox.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{
    
        private final Map<String,Song> songMap;
        private Integer autoIncrement;
        public SongRepository()
        {
            songMap=new HashMap<String,Song>();
        }

        public SongRepository (Map<String,Song> songMap)
        {
               this.songMap=new HashMap<String,Song>();
               this.autoIncrement=songMap.size();
        }

        public SongRepository (Map<String,Song> sMap,Integer a)
        {
               this.songMap=sMap;
               this.autoIncrement=sMap.size();
        }
       
       public Song save(Song song)
       {
               if(song.getSongId()==null)
               {
                     autoIncrement++;
                     Song s=new Song(Integer.toString(autoIncrement),song.getSongName(),song.getGenres(),song.getAlbum(),song.getArtistsName(),song.getArtists());
                     songMap.put(s.getSongId(), s);
                     return s;
               }

               songMap.put(song.getSongId(),song);
               return song;
       }
     public List<Song> finalAll()
     {
          return new ArrayList<>(songMap.values());
     }

     public Optional<Song> findById(String id)
     {
             return Optional.ofNullable(songMap.get(id));
     }
      public  boolean existsById(String id)
      {
              if(songMap.containsKey(id))
              {
                   return true;
              }
              return false;
      }
     public void delete(Song entity)
     {
        if(songMap.containsKey(entity.getSongId()))
        {
             songMap.remove(entity.getSongId());
        }
     }
     public void deleteById(String id)
     {
        if(songMap.containsKey(id))
        {
             songMap.remove(id);
        }
     }
    
      public List<Song> findAllSongLevelWise(Song song)
       {
                 return songMap.values().stream().filter(f->song.getSongId().equals(f.getSongId()))
                        .collect(Collectors.toList());
       }

     @Override
     public List<Song> findAll() {
          
          return new ArrayList<>(songMap.values());
     }
}
