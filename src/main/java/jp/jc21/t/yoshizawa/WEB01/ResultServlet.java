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
@WebServlet({"/language_result","/sentiment_result"})
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
			DetectedLanguage message = result.documents[0].detectedLanguage;
			switch (request.getServletPath()) {
				case "/language_result":
					request.setAttribute("message","結果：" +message.name);
					break;
				case "/sentiment_result":
					confidenceScores sentiment = Sentiment.getSentiment(string,message.iso6391Name).documents[0].confidenceScores;
					String sentiment_text= "<br>Positive:"+ sentiment.positive
							+ "<br>Neutral:" + sentiment.neutral
							+ "<br>Negative:" + sentiment.negative;
					request.setAttribute("message","結果：" +sentiment_text);
				}
			request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
