package com.crio.jukebox.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlayListRepository {
         
    
    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement=0;
    
    public PlaylistRepository()
    {
            this.playlistMap=new HashMap<String,Playlist>();
    }

    public PlaylistRepository(Map<String,Playlist> pMap)
    {
                   this.playlistMap=new HashMap<String,Playlist>();
                   this.autoIncrement=playlistMap.size();
    }
    public PlaylistRepository(Map<String,Playlist> pMap,Integer autoIncrement)
    {
             this.playlistMap=pMap;
             this.autoIncrement=playlistMap.size();
    }

   public Playlist save(Playlist playlist)
    {
        if(playlist.getId()==null)
        {
             ++autoIncrement;
            Playlist p=new Playlist(Integer.toString(autoIncrement),playlist.getUserId(),playlist.getPlayList_Name(),playlist.getListNameSongs());
            playlistMap.put(p.getPlayListId(),p);
            return p;
        }
        playlistMap.put(playlist.getPlayListId(),playlist);
        return playlist;
                
    }
   public boolean existsById(String s)
    {
        if(playlistMap.containsKey(s))
        {
              return true;
        }
        return false;

    }
   public void delete(Playlist entity)
    {
                  if(playlistMap.containsKey(entity.getPlayListId()))
                  {
                       playlistMap.remove(entity.getPlayListId());
                  }
    }
   public void deleteById(String s)
    {
        if(playlistMap.containsKey(s))
        {
             playlistMap.remove(s);
        }
    }
    public List<Playlist> findAllPlaylistLevelWise(Playlist playlist)
    {

        return playlistMap.values().stream().filter(f-> playlist.getPlayListId().equals(f.getPlayListId()))
               .collect(Collectors.toList());

    }
    @Override
    public List<Playlist> findAll() {
        
        return new ArrayList<>(playlistMap.values());
    }
    @Override
    public Optional<Playlist> findById(String id) {
         return Optional.ofNullable(playlistMap.get(id));
        
    }
}


