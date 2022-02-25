package MusicProject;
import java.util.Scanner;

public class Menu {
    
    static MyLinkedList<Song>.MyListIterator iter;
    static MyLinkedList<MyLinkedList<Song>.Node> albumList = new MyLinkedList<MyLinkedList<Song>.Node>();
    static MyLinkedList<MyLinkedList<Song>.Node>.MyListIterator albumIter = albumList.new MyListIterator();
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
    
    static void editSong(Song song){
        song.printSongData();
        System.out.println("State field and replacement as (Field), (replacement)"); 
        input = scanner.nextLine();
        // input = input.toLowerCase();
        if(input.toLowerCase().startsWith("title, ")){
            // System.out.println("changing title"); //helper
             song.setTitle(input.substring(7));
        }
    }
    static void edit(){
        listAlbums();
        listArtists();
        System.out.println("EDITING: Which album or artist would you like to start with?");
        input = scanner.nextLine();
        String albumName = "";
        albumIter.reset();
        MyLinkedList<Song>.Node albumStart = null;
        // albumIter.previousItem().getElement().printSongData(); //helper
        //Find node that starts the proper album and put the master iterator at it.
        while(albumIter.hasNext()){
            if(input.contains((albumIter.next().getElement().getAlbum()))){
                albumStart = albumIter.previousItem();      
                // albumStart.getElement().printSongData();
                iter.setLocation(albumStart);
                albumName = iter.nextItem().getAlbum();     //suspicious
                break;
            }
        }
        //Print all song titles in album
        System.out.println("The album contents are: ");
        while(iter.nextItem().getAlbum().equals(albumName)){
            System.out.println(iter.next().getTitle());
        }
        System.out.println("Which song would you like to edit?");
        input = scanner.nextLine();
        iter.setLocation(albumStart);
        while(true){
            if(input.contains(iter.next().getTitle())){
                    iter.previousItem().printSongData();
                    editSong(iter.previousItem());
                    break;
            }
        }

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
                // songs.get(2).printSongData(); //help
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
        // MyLinkedList<String> albumOptions = new MyLinkedList<String>();
        // MyLinkedList<String>.MyListIterator albumOptionsIter = albumOptions.new MyListIterator();

        String lastAlbum = "";
        iter.reset();
        while(iter.hasNext()){
            if(!iter.next().getAlbum().equals(lastAlbum)){  //If album is not the same as the last
                albumIter.add(iter.previousNode());   //HELP
                lastAlbum = iter.previousItem().getAlbum();
                // System.out.println("Just added " + iter.nextItem().album);
            }
        }
        iter.reset();
        albumIter.reset();
        System.out.println("Your albums to choose from are: " );
        while(albumIter.hasNext()){
            // albumIter.nextItem().getElement().printSongData();
            System.out.println(albumIter.next().getElement().getAlbum());
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
