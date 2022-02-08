public class Menu {
    
    static MyLinkedList<Song>.MyListIterator iter;

    static void listAlbums(){
        MyLinkedList<String> albumOptions = new MyLinkedList<String>();
        MyLinkedList<String>.MyListIterator albumIter = albumOptions.new MyListIterator();

        String lastAlbum = "";
        while(iter.hasNext()){
            if(!iter.next().album.equals(lastAlbum)){  //If album is not the same as the last
                albumIter.add(iter.nextItem().album);    //HELP
                lastAlbum = iter.nextItem().album;
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
        String lastArtist = "";
        while(iter.hasNext()){
            if(!iter.next().artist.equals(lastArtist)){  //If album is not the same as the last
                artistOptions.add(iter.nextItem().artist);    //HELP
                lastArtist = iter.nextItem().artist;
            }
        }
        iter.reset();
    }
    
    static void start(MyLinkedList<Song> songs){
        
        iter = songs.new MyListIterator();
        
        // for(int i = 0; i < songs.size(); i++){
        //     // System.out.println(songs.get(i));
        //     iter.next().printSongData();
        // }

        listAlbums();
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
}
