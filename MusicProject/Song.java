package MusicProject;
/**
 * A Song with statistics.
 */

public class Song {
    
    private String title;
    private String album;
    private String artist;
    private boolean like;
    private double rating;
    private boolean hype;
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
    private int tier;   
   /**
    * 1-- happy
    * 2-- sad
    * 
    */
    private int type; 

    //CONSTANTS
    private int TITLE_INDEX = 0;
    private int ALBUM_INDEX = 1;
    private int ARTIST_INDEX = 2;
    private int LIKE_INDEX = 3;
    private int RATING_INDEX = 4;
    private int HYPE_INDEX = 5;
    private int TIER_INDEX = 6;
    private int TYPE_INDEX = 7;

    private String splitter = "<>";

    public Song(String stringData){
        String[] songData = stringData.split(splitter); //BUG? may cause extra spaces
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
        System.out.println("Title: " + title + "\nAlbum: " + album + "\nArtist: " + artist + 
                "\nLiked: " + like + "\nRating: " + rating + "\nHype: " + hype + "\nTier: " +  tier + "\nType: " + type);
    }
    public String toString(){
        return title + splitter + album + splitter + artist + splitter + like + splitter + rating + splitter + hype + splitter +  tier + splitter + type;
    }


    //getters and setters
    public String getTitle() {
        return title;
    }
    public String getAlbum() {
        return album;
    }
    public String getArtist() {
        return artist;
    }
    public boolean isLike() {
        return like;
    }
    public double getRating() {
        return rating;
    }
    public boolean isHype() {
        return hype;
    }
    public int getTier() {
        return tier;
    }
    public int getType() {
        return type;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setLike(boolean like) {
        this.like = like;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setHype(boolean hype) {
        this.hype = hype;
    }
    public void setTier(int tier) {
        this.tier = tier;
    }
    public void setType(int type) {
        this.type = type;
    }
}
