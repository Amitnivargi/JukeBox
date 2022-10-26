package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.exception.PlaylistNotFoundException;

public class DeletePlaylistCommnad implements ICommand {

     private final IPlayListService playListService;
     public DeletePlaylistCommnad(IPlayListService playListService)
     {
          this.playListService=playListService;
     }
    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlist_id= tokens.get(2);
        try{

              playListService.deletePlaylistOfGivenId(userId, playlist_id);
              System.out.println("Delete Successfull.");
        }
        catch(PlaylistNotFoundException p)
        {
              throw new PlaylistNotFoundException("Playlist not found!");
        }
        
    }
    
}
