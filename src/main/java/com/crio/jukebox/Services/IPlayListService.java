package com.crio.jukebox.Services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlayListService {
    
           public Playlist createPlaylist (String userId,String playlist_Name,List<String> listOfSongId);
           public String deletePlaylistOfGivenId(String userId,String playlist_Id);
           public Playlist addSongInPlaylist(String userId,String playlist_Id,List<String> listSongId);
           public Playlist deleteSongInPlaylist(String useId,String playlist_Id,List<String> wantToDelete);
           public Song playPlaylist(String userId,String playlist_Id);
           public Song playSongBack(String userId);
           public Song playNextSong(String useId);
           public Song songByPreferredId(String userId,int songId);

}
