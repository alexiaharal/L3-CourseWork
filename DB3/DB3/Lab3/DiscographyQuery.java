import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.Dimension;

class DiscographyQuery {
    private static final String queryStart
	= "SELECT Release.title as \"Title\", Release.year as \"Year\", Release.type as \"Type\", Release.rating as \"Rating\", COUNT(Song.title) FROM Release,Band, Song WHERE Release.rid=Song.rid AND Release.bid=Band.bid AND Band.bid= ";
	/*
	 * This query fetches the release title as Title, release year as Year,
	 * release type as Type, release rating as Rating, the number of titles in the release
	 * as Song Num
	 * from a join of song, release and band tables.
	 * results are ordered by year
	 * grouping is used to facilitate the count function
	 */
	    private static final String queryEnd
	= "GROUP BY Release.title, Release.year, Release.type, Release.rating ORDER BY Release.year";

    public static JComponent doQuery(Statement s, int bid) 
	throws SQLException {
	ResultSet result = s.executeQuery(queryStart + bid + " " + queryEnd);

	// fill the JTable with the results
	try {
	    JTable resultTable = ResultTable.createTableFromResultSet(result);
	    resultTable.setPreferredScrollableViewportSize(new Dimension(300,100));
	    return resultTable;
	} catch(ResultTable.NoResultException e) {
	    return new JLabel("No releases recorded in database");
	}
    }
}
