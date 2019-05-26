import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;

/*
 * Implementation of Baseball Elimination using Max-flow (Ford-Fulkerson Algorithm)
 */
public class BaseballElimination {
	private final int[] w, l, r;					// The win, lose and left matches of each team
	private final int[][] g;						// The number of remaining game between each team
	private final String[] teams;	// The teams	
	
	/*
	 * Constructor that read a file
	 */
	public BaseballElimination(String filename)
	{
		In in = new In(filename);
		int numTeams = in.readInt();
		
		// Initialize the input
		int[] w_in = new int[numTeams], l_in =  new int[numTeams], r_in = new int[numTeams];
		String[] teams_in = new String[numTeams];
		int[][] g_in = new int[numTeams][numTeams];
		
		for (int i = 0; i < numTeams; i++)
		{
			teams_in[i] = in.readString();					// Store the team name
			w_in[i] = in.readInt();							// Store the number of win games
			l_in[i] = in.readInt();							// Store the number of lose games
			r_in[i] = in.readInt();							// Store the number of remaining games
			
			for (int j = 0; j < numTeams; j++)	// Store the remaining games with specific opponents
				g_in[i][j] = in.readInt();
		}
		
		w = w_in;
		l = l_in;
		r = r_in;
		g = g_in;
		teams = teams_in;
	}
	
	/*
	 * Return the number of team
	 */
	public int numberOfTeams()
	{
		return teams.length;
	}
	
	/*
	 * Return all teams
	 */
	public Iterable<String> teams()
	{
		ArrayList<String> teamsList = new ArrayList<String>();
		for (int i = 0; i < teams.length; i++)
			teamsList.add(teams[i]);
		return teamsList;
			
	}
	
	/*
	 * Return the number of wins for each given team
	 */
	public int wins(String team)
	{
		return w[validate(team)];
	}
	
	/*
	 * Return the number of losses for each given team
	 */
	public int losses(String team)
	{
		return l[validate(team)];
	}
	
	/*
	 * Return the number of remaining games for each given team
	 */
	public int remaining(String team)
	{
		return r[validate(team)];
	}
	
	/*
	 * Return the number of remaining games between team1 and team2
	 */
	public int against(String team1, String team2)
	{
		return g[validate(team1)][validate(team2)];
	}
	
	/*
	 * Is given team eliminated?
	 */
	public boolean isEliminated(String team)
	{
		int id = validate(team);
		if (isTrivial(id)) return true;
		
		
		int numOfMatches = (numberOfTeams() - 1) * (numberOfTeams() - 2) / 2;	// Number of remaining matches
		int numOfTeams = numberOfTeams() - 1;							// Number of teams validated
		
		FlowNetwork net = new FlowNetwork(numOfMatches + numOfTeams + 2);
		
		int countMatch = 0;
		for (int i = 0; i < numberOfTeams() - 2; i++)
			for (int j = i + 1; j < numberOfTeams() - 1; j++)
			{
				countMatch++;
				int id1, id2;
				if (i < id) 	id1 = i;
				else 			id1 = i + 1;	
				if (j < id)		id2 = j;
				else			id2 = j + 1;
				net.addEdge(new FlowEdge(0, countMatch, g[id1][id2]));
				net.addEdge(new FlowEdge(countMatch, numOfMatches + 1 + i, Double.POSITIVE_INFINITY));
				net.addEdge(new FlowEdge(countMatch, numOfMatches + 1 + j, Double.POSITIVE_INFINITY));
			}
		for (int i = 0; i < numOfTeams; i++)
		{
			int id1;
			if (i < id) 	id1 = i;
			else 			id1 = i + 1;
			net.addEdge(new FlowEdge(numOfMatches + 1 + i, numOfMatches + numOfTeams + 1, w[id] + r[id] - w[id1]));
		}
		FordFulkerson ff = new FordFulkerson(net, 0, numOfTeams + numOfMatches + 1);
		for (int i = 0; i < numOfTeams; i++)
			if (ff.inCut(numOfMatches + 1 + i)) return true;
		return false;
	}
	
	/*
	 * subset R of teams that eliminates given team, null if not eliminated
	 */ 
	public Iterable<String> certificateOfElimination(String team)
	{
		int id = validate(team);
		ArrayList<String> certificate = new ArrayList<String>();
		if (isTrivial(id))
		{
			for (int i = 0; i < numberOfTeams(); i++)
				if (w[i] > w[id] + r[id]) certificate.add(teams[i]);
			return certificate;
		}
		
		int numOfMatches = (numberOfTeams() - 1) * (numberOfTeams() - 2) / 2;	// Number of remaining matches
		int numOfTeams = numberOfTeams() - 1;							// Number of teams validated
		
		FlowNetwork net = new FlowNetwork(numOfMatches + numOfTeams + 2);
		
		int countMatch = 0;
		for (int i = 0; i < numberOfTeams() - 2; i++)
			for (int j = i + 1; j < numberOfTeams() - 1; j++)
			{
				countMatch++;
				int id1, id2;
				if (i < id) 	id1 = i;
				else 			id1 = i + 1;	
				if (j < id)		id2 = j;
				else			id2 = j + 1;
				net.addEdge(new FlowEdge(0, countMatch, g[id1][id2]));
				net.addEdge(new FlowEdge(countMatch, numOfMatches + 1 + i, Double.POSITIVE_INFINITY));
				net.addEdge(new FlowEdge(countMatch, numOfMatches + 1 + j, Double.POSITIVE_INFINITY));
			}
		for (int i = 0; i < numOfTeams; i++)
		{
			int id1;
			if (i < id) 	id1 = i;
			else 			id1 = i + 1;
			net.addEdge(new FlowEdge(numOfMatches + 1 + i, numOfMatches + numOfTeams + 1, w[id] + r[id] - w[id1]));
		}
		FordFulkerson ff = new FordFulkerson(net, 0, numOfTeams + numOfMatches + 1);
		for (int i = 0; i < numOfTeams; i++)
			if (ff.inCut(numOfMatches + 1 + i)) 
			{
				if (i < id) certificate.add(teams[i]);
				else 		certificate.add(teams[i + 1]);
			}
		if (certificate.isEmpty()) return null;
		else return certificate;
	}
	
	/*
	 * Validate the team's name and return the ID
	 */
	private int validate(String team)
	{
		if (team == null) throw new IllegalArgumentException();
		int i = 0;
		while (!teams[i].equals(team))
		{
			i++;
			if (i == teams.length) break;
		}
		if (i == teams.length) throw new IllegalArgumentException();
		else return i;
	}
	
	/*
	 * Is given team a trivial elimination?
	 */
	private boolean isTrivial(int id)
	{
		for (int i = 0; i < numberOfTeams(); i++)
			if (w[i] > w[id] + r[id]) return true;
		return false;
	}
	
	public static void main(String[] args)
	{
		BaseballElimination division = new BaseballElimination(args[0]);
	    for (String team : division.teams()) {
	        if (division.isEliminated(team)) {
	            StdOut.print(team + " is eliminated by the subset R = { ");
	            for (String t : division.certificateOfElimination(team)) {
	                StdOut.print(t + " ");
	            }
	            StdOut.println("}");
	        }
	        else {
	            StdOut.println(team + " is not eliminated");
	        }
	    }
	}	
}
