package com.crio.jukebox.commands;

import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.exception.SongNotFoundException;

public class ToDeleteSongInPlaylistCommand implements ICommand{

    private final IPlayListService playListService;
     public ToDeleteSongInPlaylistCommand(IPlayListService playListService)
     {
            this.playListService=playListService;
     }
    @Override
    public void execute(List<String> tokens) {
        
        String userId = tokens.get(2);
        String playlistId= tokens.get(3);
        List<String> listOfSong=Arrays.asList(tokens.get(4));

        try{
               playListService.deleteSongInPlaylist(userId, playlistId, listOfSong);
               System.out.println("Delete Successfull.");
        }catch(SongNotFoundException s)
        {
            throw new SongNotFoundException("Song Not found.");
        }
    }
    
}
