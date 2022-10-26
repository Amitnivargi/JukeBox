package com.crio.jukebox.Repositories;

import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song,String> {

         public List<Song> findAllSongLevelWise(Song song);
    
}
