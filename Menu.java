import java.util.Scanner;

public class Menu {
    
    static MyLinkedList<Song>.MyListIterator iter;
    static Scanner scanner = new Scanner(System.in);
    static String input;
    
    
    static void edit(){
        listAlbums();
        listArtists();
        System.out.println("EDITING: Which album or artist would you like to start with?");
        
    }
    static void add(){

    }
    static void start(MyLinkedList<Song> songs){
        
        iter = songs.new MyListIterator();

        System.out.println("Are we editing or adding?");
        input = scanner.nextLine();
        if(input.contains("edit")){
            edit();
        }
        else if(input.contains("add")){
            add();
        }
        else{
            System.out.println("No edit or add request was detected, please try again");
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
