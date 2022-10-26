package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.entities.Song;

public class PlayNextCommand  implements ICommand{
   private final IPlayListService playListService;
   public PlayNextCommand(IPlayListService playListService)
   {
           this.playListService=playListService;
   }
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        Song playSong = playListService.playNextSong(userId);

        System.out.println(playSong.getSongName());
        System.out.println(playSong.getAlbum());
        System.out.println(playSong.getArtists());
        
    }
    
}
