public class MediaManager{
    public static void main(String[] args){

        System.out.println("Test case 1: create a new media list from a text file");
        MediaList myMediaList = new MediaList("media.txt");
        System.out.println(myMediaList.size() + " media read from the file media.txt");

        System.out.println("\nTest case 2: find media by title (successful)");
        String title = "Beat It";
        Media media = myMediaList.findTitle(title);
        if(media == null){
            System.out.println("No media found with title \""+ title + "\" ");
        }
        else{
            System.out.println("Media found with title \"" + title + "\": ");
            System.out.println(media);
        }

        System.out.println("\nTest case 3: find media by title (fail)");
        title = "Love";
        media = myMediaList.findTitle(title);
        if(media == null){
            System.out.println("No media found with title \"" + title + "\" ");
        }
        else{
            System.out.println("Media found with title \"" + title + "\" ");
            System.out.println(media);
        }
        Media[] found = null;
      try{
        System.out.println("\nTest case 4: find media by category (two media found)");
        found = myMediaList.findCategory("Action");
        if(found == null){
            System.out.println("No media found with category \"Action\" ");
        }
        else{
            System.out.println(found.length + " media found with category \"Action\" ");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }
      try{
        System.out.println("\nTest case 5: find media by category (four media found)");
        found = myMediaList.findCategory("Pop");
        if(found == null){
            System.out.println("No media found with category: ");
        }
        else{
            System.out.println(found.length + " media found with category \"Pop\"");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }

      try{
        System.out.println("\nTest case 6: find media by category (no media found)");
        found = myMediaList.findCategory("Horror");
        if(found == null){
            System.out.println("No media found with category \"Horror\"");
        }
        else{
            System.out.println(found.length +  " media found with category \"Horror\"");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }

      try{
        System.out.println("\nTest case 7: find media by category (invalid category)");
        found = myMediaList.findCategory("Noisy");
        if(found == null){
            System.out.println("No media found with category \"Noisy\"");
        }
        else{
            System.out.println(found.length +  " media found with category \"Noisy\"");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }

      try{
        System.out.println("\nTest case 8: find media by year (one media found)");
        found = myMediaList.findYear(2000);
        if(found == null){
            System.out.println("No media found in the year 2000");
        }
        else{
            System.out.println(found.length + " media found in the year 2000");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }

      try{
        System.out.println("\nTest case 9: find media by year (more than one media found)");
        found = myMediaList.findYear(2024);
        if(found == null){
            System.out.println("No media found in the year 2024 ");
        }
        else{
            System.out.println(found.length + " media found in the year 2024");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }

      try{
        System.out.println("\nTest case 10: find media by year (no media found)");
        found = myMediaList.findYear(1980);
        if(found == null){
            System.out.println("No media found in the year 1980");
        }
        else{
            System.out.println(found.length +  " media found in the year 1980");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }

      try{
        System.out.println("\nTest case 11: find media by year (invalid year)");
        found = myMediaList.findYear(2030);
        if(found == null){
            System.out.println("No media found in the year 2030");
        }
        else{
            System.out.println(found.length +  " media found in the year 2030");
            for(Media m: found){
                System.out.println(m);
            }
        }
      }
      catch(InvalidMediaAttribute e){
        System.out.println(e.getMessage());
      }
        System.out.println("\nTest case 12: remove media (successful)");
        if(myMediaList.remove("Time After Time")){
            System.out.println("Media found with title \"Time After Time\"");
            System.out.println("Media removed successfully");
        }
        else{
            System.out.println("Media with title \"Time After Time\" not found");
        }

        System.out.println("\nTest case 13: remove media (fail)");
        if(myMediaList.remove("Love")){
            System.out.println("Media with title \"Love\" found");
            System.out.println("Media removed successfully");
        }
        else{
            System.out.println("Media with title \"Love\" not found");
        }
        
        System.out.println("\nTest case 14: view the list of media");
        System.out.println(myMediaList);

        System.out.println("\nTest case 15: sort the list by title");
        myMediaList.sort(true);
        System.out.println(myMediaList.toString());

        System.out.println("\nTest case 16: sort the list by category");
        myMediaList.sort(false);
        System.out.println(myMediaList.toString());

        System.out.println("\nTest case 17: save the media list to a text file");
        myMediaList.save("media.txt");
        System.out.println(myMediaList.size() + " media saved to the file media.txt");

        System.out.println("\nTest case 18: read the media list from the saved text file");
        myMediaList = new MediaList("media.txt");
        System.out.println(myMediaList.size() + " media read from the file media.txt");
        System.out.println(myMediaList);


        System.out.println("\nTest case 19: view the archivable media (3 media)");
        int archivable = myMediaList.findArchivable();
        System.out.println(archivable + " media may be archived");

        System.out.println("\nTest case 20: archive the archivable media (3 media)");
        archivable = myMediaList.archive();
        System.out.println(archivable + " media have been archived");

        System.out.println("\nTest case 21: view the list of archived media (3 media)");
        archivable = myMediaList.viewArchived();
        System.out.println("There are " + archivable + " media in the archive");


        System.out.println("\nTest case 22: save the media list (including archived media) to a text file");
        myMediaList.save("media.txt");
        System.out.println(myMediaList.size() + " media saved to the file media.txt");

        System.out.println("\nTest case 23: read the media list (including the archived media) from the saved text file");
        myMediaList = new MediaList("media.txt");
        System.out.println(myMediaList.size() + " media read from the file media.txt");
        System.out.println(myMediaList);

        System.out.println("\nTest case 24: view the archivable media (no archivable media)");
        archivable = myMediaList.findArchivable();
        System.out.println(archivable + " media may be archived");

        System.out.println("\nTest case 25: archive the archivable media (no media archived)");
        archivable = myMediaList.archive();
        System.out.println(archivable + " media have been archived");

        System.out.println("\nTest case 26: view the list of archived media (3 media in the archive)");
        archivable = myMediaList.viewArchived();
        System.out.println("There are " + archivable + " media in the archive");

        System.out.println("\nTest case 27: remove a media from the list (the media is archived)");
        if(myMediaList.remove("The GodFather")){
          System.out.println("Media with title \"The  GodFather\" found");
          System.out.println("Media removed successfully");
        }
        else{
          System.out.println("Media with title \"The  GodFather\" not found");
        }

        System.out.println("\nTest case 28: view the list of media");
        System.out.println(myMediaList);

        System.out.println("\nTest case 29: view the list of archived media (2 media in the archive)");
        archivable = myMediaList.viewArchived();
        System.out.println("There are " + archivable + " media in the archive");

        System.out.println("\nTest case 30: save the media list (including archived media) to a text file");
        myMediaList.save("media.txt");
        System.out.println(myMediaList.size() + " media saved to the file media.txt");

        System.out.println("\nTest case 31: read the media list (including the archived media) from the saved text file");
        myMediaList = new MediaList("media.txt");
        System.out.println(myMediaList.size() + " media read from the file media.txt");
        System.out.println(myMediaList);

        System.out.println("\nTest case 32: view the list of movies");
        myMediaList.viewMovies();
        
        System.out.println("\nTest case 33: view the list of songs");
        myMediaList.viewSongs();
    }
}