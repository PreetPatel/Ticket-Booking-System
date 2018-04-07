package tbs.client;

import java.util.*;
import tbs.server.TBSServer;
import tbs.server.TBSServerImpl;

public class CLI {
	public static void main(String[] args) {
		String path = "theatres1.csv";
		if (args.length > 0) {
			path = args[0]; // This allows a different file to be specified as an argument, but the default is theatres2.csv
		}
		TBSServer server = new TBSServerImpl();
		String result = server.initialise(path);
		System.out.println("Result from initialisation is {" + result + "}");  // Put in { } to make empty strings easier to see.
		server.dump(); // Implement dump() to print something useful here to determine whether your initialise method has worked.

		System.out.println("//////////////////////// RESULTS FROM ADDING ARTIST ////////////////////////////////////////");
		
		String artistID1 = server.addArtist("Ewan");
		System.out.println("Result from adding artist 'Ewan' is {" + artistID1 + "}");
		server.dump(); // Check that the server has been updated

		String artistID2 = server.addArtist("Ewan");
		System.out.println("Result from adding artist 'Ewan' is {" + artistID2 + "}");
		server.dump(); // Check that the server has been updated

		String artistID3 = server.addArtist("Preet");
		System.out.println("Result from adding artist 'Preet' is {" + artistID3 + "}");
		server.dump(); // Check that the server has been updated

		String artistID4 = server.addArtist("Ed Sheeran");
		System.out.println("Result from adding artist 'Ed Sheeran' is {" + artistID4 + "}");
		server.dump(); // Check that the server has been updated

		String artistID5 = server.addArtist("    ");
		System.out.println("Result from adding artist '    ' is {" + artistID5 + "}");
		server.dump(); // Check that the server has been updated

		String artistID6 = server.addArtist(null);
		System.out.println("Result from adding artist with Null name is {" + artistID6 + "}");
		server.dump(); // Check that the server has been updated

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////RESULTS FROM ADDING ACTS ////////////////////////////////////");
		
		String actID1 = server.addAct("Lecture 3b: Making Objects", artistID1, 50); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act to artist 'Ewan' is {" + actID1 + "}");
		server.dump();

		String actID2 = server.addAct("Divide Tour", artistID4, 120); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act 'Divide Tour' to artist 'Ed Sheeran' is {" + actID2 + "}");
		server.dump();

		String actID3 = server.addAct("World Tour", artistID5, 60); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act 'World Tour' to an artist which did not get added (Produced error)' is {" + actID3 + "}");
		server.dump();

		String actID4 = server.addAct("World Tour", null, 60); // this also checks that the artist ID is used properly
		System.out.println("Result from adding act 'World Tour' to a null artist is {" + actID4 + "}");
		server.dump();

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");

		System.out.print("Result from GetTheatreID is { "); //Results from GetTheatreID
		List<String> TheatreIDs = server.getTheatreIDs();
		for(String e: TheatreIDs) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.print("Result from GetArtistIDs is { "); //Results from GetArtistID
		List<String> ArtistIDs = server.getArtistIDs();
		for(String e: ArtistIDs) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.print("Result from GetArtistNames is { "); //Results from GetArtistName
		List<String> ArtistNames = server.getArtistNames();
		for(String e: ArtistNames) { System.out.print(e + " "); }
		System.out.println("}");

		System.out.print("Result from GetActIDForArtist is { "); //Results from GetArtistName
		List<String> ActNamesByArtist1 = server.getActIDsForArtist("1");
		for(String e: ActNamesByArtist1) { System.out.print(e + " "); }
		System.out.println("}");

		String schedule1 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:9t", "$200", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance on 99th of the 9Tth Month (WRONG DATE) to  ED Sheeran's Act is {" + schedule1 + "}");
		server.dump();

		String schedule2 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$200", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance on 99th of the 99th Month to  ED Sheeran's Act is {" + schedule2 + "}");
		server.dump();
	}
}
