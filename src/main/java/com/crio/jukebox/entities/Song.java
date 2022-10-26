package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{
             private final String songId;
             private final String songName;
             private final String genres;
             private final String album;
             private final String artistName;
             private final List<String> artists;
             
           public Song(String songId)
           {
              this.songId=songId;
              this.songName=getSongName();
              this.genres = getGenres();
              this.artists=getArtists();
              this.artistName=getArtistsName();
              this.album=getAlbum();
           }

           public Song (Song song){
            this.songId=song.songId;
            this.songName=song.songName;
            this.genres = song.genres;
            this.artists=song.artists;
            this.artistName=song.artistName;
            this.album=song.album;
           }
        
           public Song (String songId,String songName, String genres,String album,String artistName,List<String> artists){
            this.songId=songId;
            this.songName=songName;
            this.genres =genres;
            this.artists=artists;
            this.artistName=artistName;
            this.album=album;
           }

           public Song (String songName, String genres,String album,String artistName,List<String> artists){
            this.songName=songName;
            this.songId=getSongId();
            this.genres =genres;
            this.artists=artists;
            this.artistName=artistName;
            this.album=album;
           }
           public String getSongId()
           {
                 return songId;
           }
           public String getSongName()
           {
              return songName;
           }

           public String getGenres()
           {
                return genres;
           }

           public List<String> getArtists()
           {
              return artists;
           }

           public String getArtistsName()
           {
                return artistName;
           }

           public String getAlbum()
           {
                return album;
           }

        public int hashCode()
        {
              final int prime=31;
              int result=1;
              result=prime*result+((id==null)? 0 : id.hashCode());
              return result;
        }
        public boolean equals(Object obj)
        {
              if(this==obj)
                 return true;
              if(obj==null)
                  return false;
              if(getClass()!=obj.getClass())
                    return false; 
              Song other = (Song)obj;
              if(id==null)
              {
                  if(other.id!=null)
                      return false;

              } else if(!id.equals(other.id))
              {
                  return false;
              }
              
            return true;  
        }

        public String toString()
        {
                return "current song playing song {"+songName +"}, Album -{"+ album + "}, Artists -{"+ artists +"}";
        }
}
