package com.crio.jukebox.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.crio.jukebox.Services.IUsersService;
import com.crio.jukebox.Services.PlaylistService;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CreatePlaylistCommandTest {
    private final PrintStream standardout= System.out;

    private final ByteArrayOutputStream outputStreamCaptor= new ByteArrayOutputStream();

    @Mock
    IUsersService usersServiceMock;
    @Mock
    PlaylistService playlistServiceMock;

    @InjectMocks
    CreatePlaylistCommand createPlaylistCommand;

    @BeforeEach
    public void setUp(){System.setOut(new PrintStream(outputStreamCaptor));}

    @Test
    @DisplayName("execute method of CreatePlaylistCommand Should Print newly Created playlist To Console")
    public void execute(){
           String expectedOutput="Playlist ID - 1";
           final LinkedList<Song> songList=new LinkedList<Song>(){

            {
                    List<String> artists4 = new ArrayList<String>();
                    artists4.add("Ed Sheeran");
                    artists4.add("Skrillex");
                    add(new Song("4","Way TO Break My Heart","Pop","No.6 Collaborations Project","Ed Sheeran",artists4));
                    
                    List<String> artists5 = new ArrayList<String>();
                    artists5.add("Ed Sheeran");
                    artists5.add("Chance The Rapper");
                    artists5.add("PnB Rock");

                    add(new Song("5","Cross Me","Pop","No.6 Collaborations Project","Ed Sheeran",artists5));
                    
               
            }

           };

           Playlist playlist = new Playlist("1","name",songList);
           ArrayList<String> songId= new ArrayList<>();
           songId.add("4"); songId.add("5");
           when(playlistServiceMock.createPlaylist("1", "name", songId)).thenReturn(playlist);

           //Act
           createPlaylistCommand.execute(List.of("CREATE-PLAYLIST","1","name","4","5"));

           //Assert
           Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

           //verify

           verify(playlistServiceMock,times(1)).createPlaylist(playlist.getUserId(), playlist.getPlayList_Name(),songId);
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardout);
    }



}
