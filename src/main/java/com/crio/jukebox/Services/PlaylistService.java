package com.crio.jukebox.Services;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
//import com.crio.codingame.entities.User;
//import com.crio.codingame.repositories.IUserRepository;
import com.crio.jukebox.Repositories.IPlayListRepository;
import com.crio.jukebox.Repositories.ISongRepository;
import com.crio.jukebox.Repositories.IUsersRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.Users;
import com.crio.jukebox.exception.PlaylistNotFoundException;
import com.crio.jukebox.exception.SongNotFoundException;
import com.crio.jukebox.exception.UsersNotFoundException;

public class PlaylistService implements IPlayListService{

    private final IPlayListRepository _iPlayListRepository;
    private final IUsersRepository _iUsersRepository;
    private final ISongRepository _iSongRepository;

    public PlaylistService(IPlayListRepository iPlayListRepository,IUsersRepository iUsersRepository,ISongRepository iSongRepository)
    {
             _iPlayListRepository=iPlayListRepository;
             _iUsersRepository = iUsersRepository;
             _iSongRepository = iSongRepository;
    }
    @Override
    public Playlist createPlaylist(String userId, String playlist_Name, List<String> listOfSongId) {
         final Users user = _iUsersRepository.findById(userId).orElseThrow(()-> new UsersNotFoundException("Cannot create playlist. Please try again."));
         LinkedList<Song> songs = new LinkedList<>();
         for(String s: listOfSongId)
         {
              Song song = _iSongRepository.findById(s).orElseThrow(()-> new SongNotFoundException("Cannot find the song.Please enter valid id."));
              songs.add(song);
         }

         if(listOfSongId.isEmpty())
         {
                return _iPlayListRepository.save(new Playlist(userId,playlist_Name,songs));
         }
         return _iPlayListRepository.save(new Playlist(userId,playlist_Name,songs));
    }

    @Override
    public String deletePlaylistOfGivenId(String userId, String playlist_Id) throws PlaylistNotFoundException,UsersNotFoundException  {
           Users  u = _iUsersRepository.findById(userId).orElseThrow(()-> new UsersNotFoundException("Cannot delete playlist."));
           Playlist p = _iPlayListRepository.findById(playlist_Id).orElseThrow(()-> new PlaylistNotFoundException("Cannot "));

           u.deletePlayList(p);
           return "successFully Deleted";
        
    }

    @Override
    public Playlist addSongInPlaylist(String userId, String playlist_Id, List<String> listSongId) throws SongNotFoundException,PlaylistNotFoundException {
        Playlist p = _iPlayListRepository.findById(playlist_Id).orElseThrow(()-> new PlaylistNotFoundException("Cannot found"));
        LinkedList<Song> songs = p.getListNameSongs();
        List<Song> newSongUserWantsToAdd = new LinkedList<>();
        for(String songId : listSongId)
        {
              Song song = _iSongRepository.findById(songId).orElseThrow(()-> new SongNotFoundException("Cannot find song for this songId "));
              newSongUserWantsToAdd.add(song);
        }

        for(Song song : newSongUserWantsToAdd)
        {
                if(songs.stream().noneMatch(m->song.getSongId().equals(m.getSongId())))
                {
                      songs.add(song);
                }
        }
        return p;
    }

    @Override
    public Playlist deleteSongInPlaylist(String useId, String playlist_Id,
            List<String> wantToDelete) throws PlaylistNotFoundException {
            Playlist p = _iPlayListRepository.findById(playlist_Id).orElseThrow(()->new PlaylistNotFoundException("Cannot find "));
            LinkedList<Song> songs=p.getListNameSongs();
            for(String s1: wantToDelete)
            {
                  songs.removeIf(data-> Objects.equals(s1,data.getSongId()));
            }
        
        return p;
    }

    @Override
    public Song playPlaylist(String userId, String playlist_Id) {
        Playlist playlist = _iPlayListRepository.findById(playlist_Id).orElseThrow(()->new PlaylistNotFoundException("Cannot found"));
        LinkedList<Song> songs = playlist.getListNameSongs();
        return songs.getFirst();
    }

    @Override
    public Song playSongBack(String userId) {
        List<Playlist> playlist = _iPlayListRepository.findAll();
          if(playlist==null)
          {
             throw new PlaylistNotFoundException("sorry There is no any active playlist");
          }

          Playlist activePlaylist = playlist.get(0);
          String playlist_Id=activePlaylist.getPlayListId();
          LinkedList<Song> songs = activePlaylist.getListNameSongs();
          Song currentSongPlaying= playPlaylist(userId, playlist_Id);
          int indexOfCurrentSong =  songs.indexOf(currentSongPlaying);
          if(indexOfCurrentSong==0)
          {
               return songs.getLast();
          }

           
        return songs.get(--indexOfCurrentSong);
    }

    @Override
    public Song playNextSong(String userId) {
        List<Playlist> playlist = _iPlayListRepository.findAll();
        if(playlist==null)
        {
           throw new PlaylistNotFoundException("sorry There is no any active playlist!");
        }

        Playlist activePlaylist = playlist.get(0);
        String playlist_Id=activePlaylist.getPlayListId();
        LinkedList<Song> songs = activePlaylist.getListNameSongs();
        Song currentSongPlaying= playPlaylist(userId, playlist_Id);
        int indexOfCurrentSong =  songs.indexOf(currentSongPlaying);
        if(indexOfCurrentSong==songs.size())
        {
             return songs.getFirst();
        }

         
      return songs.get(++indexOfCurrentSong);
    }

    @Override
    public Song songByPreferredId(String userId, int songId) {
        Playlist playlist = _iPlayListRepository.findById(userId).orElseThrow(()-> new PlaylistNotFoundException("cannot found"));
        LinkedList<Song> songs= playlist.getListNameSongs();

        Song currentSong;
        try{
               currentSong = songs.get(songId);
               return currentSong;
        }catch(SongNotFoundException e)
        {
               throw new SongNotFoundException("Given id of songs is not available in playlist!");
        }

    }
    
}
