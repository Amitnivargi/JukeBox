package com.crio.jukebox.appConfigs;
import com.crio.jukebox.Repositories.IPlayListRepository;
import com.crio.jukebox.Repositories.ISongRepository;
import com.crio.jukebox.Repositories.PlaylistRepository;
import com.crio.jukebox.Repositories.SongRepository;
import com.crio.jukebox.Repositories.UsersRepository;
import com.crio.jukebox.Services.IPlayListService;
import com.crio.jukebox.Services.ISongServcie;
import com.crio.jukebox.Services.IUsersService;
import com.crio.jukebox.Services.PlaylistService;
import com.crio.jukebox.Services.SongService;
import com.crio.jukebox.Services.UsersService;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUsersCommand;
import com.crio.jukebox.commands.DeletePlaylistCommnad;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.PlayNextCommand;
import com.crio.jukebox.commands.PlayPereferredIdSongCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongBackCommand;
import com.crio.jukebox.commands.ToAddSongCommand;
import com.crio.jukebox.commands.ToDeleteSongInPlaylistCommand;

public  class ApplicationConfig {

           private final UsersRepository usersRepository = new UsersRepository();
           private final ISongRepository songRepository = new SongRepository();
           private final IPlayListRepository playListRepository = new PlaylistRepository();
           private final IUsersService usersService = new UsersService(usersRepository);
           private final ISongServcie songServcie = new SongService(songRepository);
           private final IPlayListService pListService = new PlaylistService(playListRepository, usersRepository, songRepository);
           private final CreateUsersCommand createUsersCommand = new CreateUsersCommand(usersService);
           private final LoadDataCommand loadDataCommand = new LoadDataCommand(songServcie);
           private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(pListService);
           private final DeletePlaylistCommnad deletePlaylistCommnad = new DeletePlaylistCommnad(pListService);
           private final ToAddSongCommand toAddSongCommand = new ToAddSongCommand(pListService);
           private final ToDeleteSongInPlaylistCommand toDeleteSongInPlaylistCommand = new ToDeleteSongInPlaylistCommand(pListService);
           private final PlayPlayListCommand playPlayListCommand = new PlayPlayListCommand(pListService);
           private final PlaySongBackCommand playSongBackCommand = new PlaySongBackCommand(pListService);
           private final PlayNextCommand playNextSongCommand = new PlayNextCommand(pListService);
           private final PlayPereferredIdSongCommand playPereferredIdSongCommand = new PlayPereferredIdSongCommand(pListService); 
           private final CommandInvoker commandInvoker = new CommandInvoker();
           public CommandInvoker getCommandInvoker(){
                    commandInvoker.register("LOAD-DATA",loadDataCommand);
                    commandInvoker.register("CREATE-USER",createUsersCommand);
                    commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
                    commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommnad);
                    commandInvoker.register("MODIFY-PLAYLIST ADD",toAddSongCommand);
                    commandInvoker.register("MODIFY-PLAYLIST DELETE",toDeleteSongInPlaylistCommand);
                    commandInvoker.register("PLAY-PLAYLIST",playPlayListCommand);
                    commandInvoker.register("PLAY-SONG BACK",playSongBackCommand);
                    commandInvoker.register("PLAY-SONG NEXT",playNextSongCommand);
                    commandInvoker.register("PLAY-SONG PREFERREDID",playPereferredIdSongCommand);

                    return commandInvoker;
           }
       
}