package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String string = "生姜焼定食";
		try {
			Language result = Json05.getLanguage(string);
			String message = result.documents[0].detectedLanguage.name;
			String l= result.documents[0].detectedLanguage.iso6391Name;
			Setiment sen = Sentiment.getSentiment(string, l);
			request.setAttribute("message", message);
			System.out.print((String)sen.documents[0].confidenceScores.positive.toString());
			System.out.print((String)sen.documents[0].confidenceScores.neutral.toString());
			System.out.print((String)sen.documents[0].confidenceScores.negative.toString());
			request.setAttribute("positive", sen.documents[0].confidenceScores.positive.toString());
			request.setAttribute("neutral", sen.documents[0].confidenceScores.neutral.toString());
			request.setAttribute("negative", sen.documents[0].confidenceScores.negative.toString());
			request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String string = request.getParameter("string");
		request.setAttribute("string", string);

		try {
			Language result = Json05.getLanguage(string);
			String message = result.documents[0].detectedLanguage.name;
			String l= result.documents[0].detectedLanguage.iso6391Name;
			Setiment sen = Sentiment.getSentiment(string, l);
			request.setAttribute("message", message);
			request.setAttribute("positive", sen.documents[0].confidenceScores.positive.toString());
			request.setAttribute("neutral", sen.documents[0].confidenceScores.neutral.toString());
			request.setAttribute("negative", sen.documents[0].confidenceScores.negative.toString());
			request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
