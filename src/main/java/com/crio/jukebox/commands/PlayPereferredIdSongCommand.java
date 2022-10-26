package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exception.SongNotFoundException;

public class PlayPereferredIdSongCommand  implements ICommand{

    private final IPlayListService playListService;
    public PlayPereferredIdSongCommand(IPlayListService playListService)
    {
        this.playListService=playListService;
    }
    @Override
    public void execute(List<String> tokens) {
        String userId=tokens.get(1);
        int id=Integer.parseInt(tokens.get(2));

        try{

                   Song song=playListService.songByPreferredId(userId, id);
                   System.out.println("Current song playing..");
                   System.out.println(song.getSongName());
                   System.out.println(song.getAlbum());
                   System.out.println(song.getArtists());
        }catch(SongNotFoundException s)
        {
            throw new SongNotFoundException("Song not found in current active playlist.");
        }
        
    }
    
}
