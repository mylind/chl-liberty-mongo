package wasdev.sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final long serialVersionUID = 1L;
    @Resource(name = "mongo/Compose for MongoDB-3r")
	protected DB db;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
		if (db == null) {
			pw.println("Uh oh! Something went wrong. DB is null, please check the server logs.");
		} else {
			pw.println("HelloMongo!");
		}

		try {
			DBCollection col = db.getCollection("user");
			long count = col.getCount();
			pw.println("<br>[count] collection count = " + count + " from [sampledb]<br>");
			pw.println("[inserting] 10<br>");
			for (int i = 0; i < 10; i++)
				col.insert(new BasicDBObject("user", count + i));

			pw.println("[dump entire db] : <br>");
			DBCursor cursor = col.find();
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				pw.println(obj+"<br>");
			}

		} catch (MongoException me) {
			pw.println("Caught a mongo exception.");
			me.printStackTrace(pw);
		}
    }

}
