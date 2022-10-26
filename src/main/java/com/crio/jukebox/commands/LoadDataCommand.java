package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.Services.ISongServcie;
import com.crio.jukebox.exception.SongNotFoundException;

public class LoadDataCommand implements ICommand {

    private final ISongServcie  songServcie;
    public LoadDataCommand(ISongServcie songServcie)
    {
        this.songServcie=songServcie;
    }
    @Override
    public void execute(List<String> tokens) {
        String csvFile=tokens.get(1);
        try{
                 songServcie.loadData(csvFile);
                 System.out.println("Song loaded Successfulyy!");
        }
        catch(SongNotFoundException s)
        {
              throw new SongNotFoundException("Song not Found.");
        }
        
    }
    
}
