package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasFewerThan(15, "assists"),
                             new PlaysIn("PHI")
        );
        
        Matcher m2 = new Or( new HasFewerThan(2, "goals"),
                             new HasFewerThan(5, "assists")
        );
        Matcher m3 = new Not(new Not( new PlaysIn("PHI"))
        );
        
        QueryBuilder query = new QueryBuilder();
       
        Matcher m4 = query.oneOf(
                        query.playsIn("PHI")
                             .hasAtLeast(10, "goals")
                             .hasFewerThan(15, "assists").build(),
 
                        query.playsIn("EDM")
                             .hasAtLeast(50, "points").build()
                       ).build();
        
        
        for (Player player : stats.matches(m4)) {
            System.out.println( player );
        }
    }
}
