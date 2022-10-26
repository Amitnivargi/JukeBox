package com.crio.jukebox.entities;

import java.util.LinkedList;


public class Playlist extends BaseEntity {
         
          private String UserId;
          private String playlistId;
          private final String playList_Name;

          private final LinkedList<Song> listNameSong;

          public Playlist(Playlist playlist)
          {
               this(playlist.playlistId,playlist.UserId,playlist.playList_Name,playlist.listNameSong);
          }

        public Playlist(String playListId,String userId,String playlist_Name,LinkedList<Song> listname_song )
        {
                this.UserId=userId;
                this.playlistId=playListId;
                this.playList_Name = playlist_Name;
                this.listNameSong = listname_song;
        }

        public Playlist(String userId, String playlist_Name, LinkedList<Song> listname_song)
        {
               this.playList_Name=playlist_Name;
               this.listNameSong = listname_song;
               this.UserId = userId;
        }
        
     public String getPlayListId()
     {
         return playlistId;
     }

     public String getPlayList_Name()
     {
          return playList_Name;
     }

     public String getUserId()
     {
           return UserId;
     }

     public LinkedList<Song> getListNameSongs()
     {
            return listNameSong;
     }
     
    //  public String toString(){
              
    //           return "CRATE-PLAYLIST "+this.getUserId()+" MY_PLAYLIST_"+;
    //  }
}
