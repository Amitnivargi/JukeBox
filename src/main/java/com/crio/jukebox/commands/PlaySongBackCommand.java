package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.entities.Song;

public class PlaySongBackCommand implements ICommand {

    private final IPlayListService playListService;
    public PlaySongBackCommand(IPlayListService playListService)
    {
        this.playListService=playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        String userId= tokens.get(1);
        Song song = playListService.playSongBack(userId);
        System.out.println(song.getSongName());
        System.out.println(song.getAlbum());
        System.out.println(song.getArtists());
        
    }
    
    
}
