package com.crio.jukebox.Repositories;

import java.util.List;
import com.crio.jukebox.entities.Playlist;

public interface IPlayListRepository extends CRUDRepository<Playlist,String>{
          
        Playlist save(Playlist playlist);
        boolean existsById(String s);
        void delete(Playlist entity);
        void deleteById(String s);
        public List<Playlist> findAllPlaylistLevelWise(Playlist playlist);
}
