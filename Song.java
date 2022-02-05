public class Song {
    
    String title;
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

    public Song(String stringData){
        String[] songData = stringData.split(", "); //BUG? may cause extra spaces
        title = songData[0];
        artist = songData[1];
        if(songData[2].equals("true")){
            like = true;
        } else { like = false; }
        rating = (double) Double.parseDouble(songData[3]);
        if(songData[4].equals("true")){
            hype = true;
        } else{ hype = false; }
        tier = Integer.parseInt(songData[5]);
        type = Integer.parseInt(songData[6]);
    }

    public void printSongData(){
        System.out.println("Song -- " + title + " by " + artist + " is rated " + rating);
    }
    public String toString(){
        return title + ", " + artist + ", " + like + ", " + rating + ", " + hype + ", " +  tier + ", " + type;
    }
}
