package org.javimelli.dao;

import org.javimelli.model.Vote_comment;
import java.sql.Connection;
import java.util.List;

public interface Vote_commentDao {

	public List<Vote_comment> getByUser_id(int user);
	public List<Vote_comment> getByCommentary_id(int commentary);
	public Vote_comment getByUserAndCommentary(int user, int commentary);
	public int numComplaint(int comment);
	public int numVotePositeve(int comment);
	public int numVoteNegative(int comment);
	public int add(Vote_comment vote);
	public boolean save(Vote_comment vote);
	public void setConnection(Connection conn);
}
