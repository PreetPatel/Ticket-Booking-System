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

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////RESULTS FROM ADDING Performance /////////////////////////////");


		String schedule1 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:9t", "$200", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance on 99th of the 9Tth Month (WRONG DATE) to  ED Sheeran's Act is {" + schedule1 + "}");
		server.dump();

		String schedule2 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$200", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance on 99th of the 99th Month to  ED Sheeran's Act is {" + schedule2 + "}");
		server.dump();

		String schedule3 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$200$", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance with wrong currency format ($100$) to  ED Sheeran's Act is {" + schedule3 + "}");
		server.dump();

		String schedule4 = server.schedulePerformance(actID2, "T1", "9999-99-99T99:99", "$022000", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance with wrong currency format ($022000) to  ED Sheeran's Act is {" + schedule4 + "}");
		server.dump();

		String schedule5 = server.schedulePerformance(actID2, "T2", "9999-99-99T99:99", "$220", "$100"); // this also checks that the artist ID is used properly
		System.out.println("Result from adding performance in Theatre 2 for  ED Sheeran's Act is {" + schedule5 + "}");
		server.dump();

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");

		String ticket1 = server.issueTicket(schedule2,1,1);
		System.out.println("Result from purchasing premium tickets to  ED Sheeran's Performance is {" + ticket1 + "}");

		String ticket2 = server.issueTicket(schedule1,1,1);
		System.out.println("Result from purchasing tickets to  a non-Existing Performance is {" + ticket2 + "}");

		String ticket3 = server.issueTicket(schedule2,1,1);
		System.out.println("Result from purchasing non available tickets to  ED Sheeran's Performance is {" + ticket3 + "}");

		String ticket5 = server.issueTicket(schedule5,1,1);
		System.out.println("Result from purchasing tickets to  ED Sheeran's Performance in Theatre 2 is {" + ticket5 + "}");

		System.out.println("///////////////HURRY! ED SHEERAN'S PERFORMANCE IS SELLING OUT!//////////////////////////////");
		int counter = 0;
		for (int i = 1; i< 8; i++ ) {
			for (int j = 1; j<8;j++) {
				server.issueTicket(schedule2, i, j);
				counter++;
			}
		}
		System.out.println("Number of Tickets issued: " + counter);

		System.out.println("///////////////WOW! ED SHEERAN'S PERFORMANCE IS NOW SOLD OUT!///////////////////////////////");

		String ticket4 = server.issueTicket(schedule2,6,2);
		System.out.println("Result from purchasing tickets to  ED Sheeran's sold out Performance is {" + ticket4 + "}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.print("Result from GetTicketIDsForPerformance for ED Sheeran's Performance in theatre 1 is { "); //Results from GetTheatreID
		List<String> ticketIDs = server.getTicketIDsForPerformance(schedule2);
		for(String e: ticketIDs) { System.out.print(e + " ,"); }
		System.out.println("}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.print("Result from GetAvailableSeatsForPerformance for ED Sheeran's Performance in theatre 1 is { "); //Results from GetTheatreID
		List<String> availableSeats = server.seatsAvailable(schedule2);
		for(String e: availableSeats) { System.out.print(e + " , "); }
		System.out.println("}");

		System.out.print("Result from GetAvailableSeatsForPerformance for ED Sheeran's Performance in theatre 2 is { "); //Results from GetTheatreID
		List<String> availableSeats1 = server.seatsAvailable(schedule5);
		for(String e: availableSeats1) { System.out.print(e + " , "); }
		System.out.println("}");

		System.out.print("Result from GetAvailableSeatsForPerformance for ED Sheeran's Performance for a non existing performance is { "); //Results from GetTheatreID
		List<String> availableSeats2 = server.seatsAvailable(schedule1);
		for(String e: availableSeats2) { System.out.print(e + " , "); }
		System.out.println("}");

		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.print("Result from sales report for Ed Sheeran's World Tour Act is { \n"); //Results from GetTheatreID
		List<String> salesReport1 = server.salesReport(actID2);
		for(String e: salesReport1) { System.out.print(e + "\n"); }
		System.out.println("}");

	}
}
