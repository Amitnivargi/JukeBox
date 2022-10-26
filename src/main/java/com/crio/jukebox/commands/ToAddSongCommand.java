package com.crio.jukebox.commands;

import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exception.SongNotFoundException;

public class ToAddSongCommand implements ICommand {

    private IPlayListService playListService;
    public ToAddSongCommand(IPlayListService playListService){
        this.playListService=playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(2);
        String playlistId= tokens.get(3);
        List<String> listOfSong=Arrays.asList(tokens.get(4));

        try{
                 Playlist playlist = playListService.addSongInPlaylist(userId, playlistId, listOfSong);
                 System.out.println(playlist.getPlayListId());
                 System.out.println(playlist.getPlayList_Name());
                 System.out.println(playlist.getListNameSongs());
                 
        }catch(SongNotFoundException s){
            throw new SongNotFoundException("Some Requested songs not available. Please try again.");
    }
}
    
}
