package com.crio.jukebox.commands;

import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.exception.PlaylistNotFoundException;

public class CreatePlaylistCommand implements ICommand {

            private final IPlayListService playListService;
            public CreatePlaylistCommand(IPlayListService playListService)
            {
                 this.playListService=playListService;
            }
            @Override
            public void execute(List<String> tokens) throws PlaylistNotFoundException{
                   String userId=tokens.get(1);
                   String playlistName = tokens.get(2);

                   List<String> listOfSongId = Arrays.asList((tokens.get(3).split(",")));

                   try{

                           Playlist playlist = playListService.createPlaylist(userId, playlistName, listOfSongId);
                           System.out.println(playlist);
                   }
                   catch(PlaylistNotFoundException e)
                   {
                      throw new PlaylistNotFoundException("Some requested songs are Not available. Please try again.");
                   }
                
            }


    
}
