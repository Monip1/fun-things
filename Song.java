public class Song {
    
    String title;
    String album;
    String artist;
    boolean like;
    double rating;
    boolean hype;
    /**
    * 1-- S Ult
    * 2-- A Love
    * 3-- B Stan
    * 4-- C Like
    * 5-- D Enjoy
    * 6-- E No Opinion
    * 7-- F Don't Know
    * 8-- G Dislike
    */
   int tier;   
   /**
    * 1-- happy
    * 2-- sad
    * 
    */
   int type; 

    //CONSTANTS
    int TITLE_INDEX = 0;
    int ALBUM_INDEX = 1;
    int ARTIST_INDEX = 2;
    int LIKE_INDEX = 3;
    int RATING_INDEX = 4;
    int HYPE_INDEX = 5;
    int TIER_INDEX = 6;
    int TYPE_INDEX = 7;

    

    public Song(String stringData){
        String[] songData = stringData.split(";"); //BUG? may cause extra spaces
        title = songData[TITLE_INDEX];
        album = songData[ALBUM_INDEX];
        artist = songData[ARTIST_INDEX];
        if(songData[LIKE_INDEX].equals("true")){
            like = true;
        } else { like = false; }
        rating = (double) Double.parseDouble(songData[RATING_INDEX]);
        if(songData[HYPE_INDEX].equals("true")){
            hype = true;
        } else{ hype = false; }
        tier = Integer.parseInt(songData[TIER_INDEX]);
        type = Integer.parseInt(songData[TYPE_INDEX]);
    }

    public void printSongData(){
        System.out.println("Song -- " + title + " by " + artist + " in " + album + " is rated " + rating);
    }
    public String toString(){
        return title + ";" + album + ";" + artist + ";" + like + ";" + rating + ";" + hype + ";" +  tier + ";" + type;
    }
}
