package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exception.PlaylistNotFoundException;

public class PlayPlayListCommand implements ICommand{

    private final IPlayListService playListService;

    public PlayPlayListCommand(IPlayListService playListService)
    {
          this.playListService=playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        String userId=tokens.get(1);
        String playlistId=tokens.get(2);

        try{

                 Song playSong=playListService.playPlaylist(userId, playlistId);
                 System.out.println("Current song is playing..");
                 System.out.println(playSong.getSongName());
                 System.out.println(playSong.getAlbum());
                 System.out.println(playSong.getArtists());
        }catch(PlaylistNotFoundException p)
        {
             throw new RuntimeException("Playlist is empty.");
        }
        
    }
    
    
}
