import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;



class Handler implements URLHandler { 

    ArrayList<String>queries = new ArrayList<String>(); 
    


    public String handleRequest(URI url) { 

        if (url.getPath().equals("/")) { 
            return "Start by adding words";
        }
        if (url.getPath().contains("add")==false && url.getPath().contains("contains")==false) { 
            return "404 Not Found";
        }

        String[] query = url.getQuery().split("=");
        

        if (url.getPath().contains("add")) { 
            add(query);
            return "Word added";
        }
        else if (url.getPath().contains("search")) { 
            return "Words that have above word are: " + search(query); 
        }

        return "404 Not Found!";
    }

    private String search(String[] query) {
        String sub = ""; 
        for (int i = 0; i<queries.size(); i++) { 
            if (queries.get(i).indexOf(query[1])!=-1) { 
                sub+=queries.get(i) + ", ";
            }
        }
        return sub;
    }


    private void add(String[] query) {
        queries.add(query[1]); 
    } 


}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}