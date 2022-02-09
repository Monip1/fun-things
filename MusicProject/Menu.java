package MusicProject;
import java.util.Scanner;

public class Menu {
    
    static MyLinkedList<Song>.MyListIterator iter;
    static Scanner scanner = new Scanner(System.in);
    static String input;
    static int irritation = 0;
    static Response currentResponse = Response.START;

    static enum Response {
 
        //named in levels of irritation of the computer
        START("No edit or add request was detected, please try again", 1), 
        SLIGHTLY("Still no edit or add request detected, try again", 3), 
        VERY("You gotta type edit or add so you can do things", 4), 
        COMPLETELY("ADD or EDIT, there are no other options", 5), 
        LOST("I CAN'T WITH YOU, ADD OR EDIT CHILD", 10),
        BROKEN("you broke it.", 12);
     
        private String output;
        private int breakingPoint;
     
        private Response(String s, int n) {
            output = s;
            breakingPoint = n;
        }
     
        public String getResponse() {
            return output;
        }
        public Menu.Response next(){
            if(ordinal() + 1 < values().length){
                return values()[(ordinal() + 1)];
            }
            return values()[values().length-1];
        }
        public int getBreakingPoint(){
            return breakingPoint;
        }
    }

    static String GREETING = "Welcome to some music stats n' stuff ";
    
    
    static void edit(){
        listAlbums();
        listArtists();
        System.out.println("EDITING: Which album or artist would you like to start with?");
        
    }
    static void add(){

    }
    static void start(MyLinkedList<Song> songs){
        
        iter = songs.new MyListIterator();

        System.out.println(GREETING);
        while(true){
            System.out.println("Are we editing or adding?");
            input = scanner.nextLine();
            if(input.contains("edit")){
                edit();
                break;
            }
            else if(input.contains("add")){
                add();
                break;
            }
            else{
                irritation ++;
                while(irritation > currentResponse.getBreakingPoint()){
                    currentResponse = currentResponse.next();
                }
                System.out.println(currentResponse.getResponse());
                if(currentResponse.equals(Response.BROKEN)){
                    return;
                }
            }
        }
        /**
         * TODO: list out albums and artists to choose from
         */
        /**
         * TODO: select one and give recommendations for typos
         */
        /**
         * TODO:Display album or artist
         */
        /**
         * TODO: Choose Song
         */
        /**
         * TODO: make edits to song
         */
        /**
         * TODO: back to album/ arist and either stay or go back to all
         */


    } 

    static void listAlbums(){
        MyLinkedList<String> albumOptions = new MyLinkedList<String>();
        MyLinkedList<String>.MyListIterator albumIter = albumOptions.new MyListIterator();

        String lastAlbum = "";
        while(iter.hasNext()){
            if(!iter.next().getAlbum().equals(lastAlbum)){  //If album is not the same as the last
                albumIter.add(iter.nextItem().getAlbum());    //HELP
                lastAlbum = iter.nextItem().getAlbum();
                // System.out.println("Just added " + iter.nextItem().album);
            }
        }
        iter.reset();
        albumIter.reset();
        System.out.println("Your albums to choose from are: " );
        while(albumIter.hasNext()){
            System.out.println(albumIter.next());
        }
    }
    static void listArtists(){
        MyLinkedList<String> artistOptions = new MyLinkedList<String>();
        MyLinkedList<String>.MyListIterator artistIter = artistOptions.new MyListIterator();

        String lastArtist = "";
        while(iter.hasNext()){
            if(!iter.next().getArtist().equals(lastArtist)){  //If artist is not the same as the last
                artistIter.add(iter.nextItem().getArtist());    //HELP
                lastArtist = iter.nextItem().getArtist();
                // System.out.println("Just added " + iter.nextItem().artist);
            }
        }
        iter.reset();
        artistIter.reset();
        System.out.println("Your artists to choose from are: " );
        while(artistIter.hasNext()){
            System.out.println(artistIter.next());
        }
    }
}
