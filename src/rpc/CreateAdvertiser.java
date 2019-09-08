package rpc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

/**
 * Servlet implementation class CreateAdvertiser
 */
@WebServlet("/CreateAdvertiser")

/**
 * CREATE TABLE advertiser (
    advertiser_id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    budget int,
    PRIMARY KEY (advertiser_id)
);
 */
public class CreateAdvertiser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdvertiser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection conn = DBConnectionFactory.getConnection();

		try {
			JSONObject input = RpcHelper.readJsonObject(request);
			String advertiser_name = input.getString("name");
			double budget = input.getDouble("budget");
			long advertiser_id = conn.createAdvertiser(advertiser_name, budget);
			RpcHelper.writeJsonObject(response,
					new JSONObject().put("advertiser_id", advertiser_id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}	
}
