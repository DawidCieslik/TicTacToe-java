package pl.polsl.lab.dcieslik.tictactoe.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.lab.dcieslik.tictactoe.exceptions.InvalidNameException;
import pl.polsl.lab.dcieslik.tictactoe.model.Game;

/**
 * Main class of the servlet that demonstrates parameter download given during
 * servlet initialization
 *
 * @author Gall Anonim
 * @version 1.0
 */
@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static Game game;

    /**
     * Checks if name consists only of letters.
     *
     * @param firstPlayerName first player to compare names.
     * @param secondPlayerName second player to compare names.
     * @throws pl.polsl.lab.dcieslik.tictactoe.exceptions.InvalidNameException
     * throws exception if at least one of player name is invalid
     */
    public void validate(String firstPlayerName, String secondPlayerName) throws InvalidNameException {
        if (firstPlayerName.equals("") || secondPlayerName.equals("")) {
            throw new InvalidNameException("Nazwy graczy nie mogą być puste.");
        } else if (firstPlayerName.equals(secondPlayerName)) {
            throw new InvalidNameException("Nazwy graczy nie mogą być takie same.");
        }
    }

    public void print(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        if (!request.getCharacterEncoding().equals("UTF-8")) {
            request.setCharacterEncoding("UTF-8");
        }
        PrintWriter out = response.getWriter();

        if (game.getWinner().equals("")) {
            out.print("<html>\n<head>\n<style>\n");
            out.print(".grid-container {\n\tdisplay: grid;\n\tgrid-template-columns: 12% 12% 12%;\n\tpadding: 10px;\n\tjustify-content: center;\n}\n");
            out.print(".grid-item {\n\tbackground-color: rgba(33, 150, 243, 0.2);\n\tborder: 1px solid rgba(0, 0, 0, 0.8);\n\tpadding: 38% 0%;\n\tfont-size: 30px;\n\ttext-align: center;\n}\n");
            out.print("</style>\n</head>\n<body style=\"text-align:center\">\n");
            out.print("<h1 style=\"padding-top: 20px\">Tic Tac Toe</h1>\n");
            out.print("<form action=\"game\" method=\"POST\">\n<div class=\"grid-container\">\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[0] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[1] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[2] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[3] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[4] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[5] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[6] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[7] + " class=\"grid-item\" />\n");
            out.print("\t<input type=\"submit\" name=\"button\" value=" + game.getBoard()[8] + " class=\"grid-item\" />\n");
            out.print("</div>\n</form>\n");
            out.print("<h3>" + game.getPlayerName() + "'s Turn" + " (" + game.getTurn() + ")</h3>\n");
            out.print("<div style=\"padding: 10px; color: red;\">" + game.getError() + "</div>\n");
            out.print("</body>\n</html>");
        } else {
            out.print("<html>\n<head>\n<style>\n");
            out.print(".grid-container {\ndisplay: grid;\ngrid-template-columns: 12% 12% 12%;\npadding: 10px;\njustify-content: center;\n}\n");
            out.print(".grid-item {\nbackground-color: rgba(33, 150, 243, 0.2);\nborder: 1px solid rgba(0, 0, 0, 0.8);\npadding: 38% 0%;\nfont-size: 30px;\ntext-align: center;\n}\n");
            out.print("</style>\n</head>\n<body style=\"text-align:center\">\n");
            out.print("<h1 style=\"padding-top: 380px;\ncolor: red;\">Koniec gry</h1>\n");
            if (game.getWinner().equals("O") || game.getWinner().equals("X")) {
                out.print("<h3>Wygrał " + game.getWinnerName() + ", gratulacje!</h3>\n");
            } else if (game.getWinner().equals("draw")) {
                out.print("<h3>Gra zakończona remisem.</h3>\n");
            }
            out.print("<form action=\"game\" method=\"GET\">\n");
            out.print("\t<input type=\"hidden\" size=\"20\" name=\"firstplayer\" value=" + game.getFirstPlayer().getName() + " />\n");
            out.print("\t<input type=\"hidden\" size=\"20\" name=\"secondplayer\" value=" + game.getSecondPlayer().getName() + " />\n");
            out.print("\t<input type=\"submit\" value=\"Rozpocznij nową grę\" />\n");
            out.print("</form>\n</body>\n</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        request.setCharacterEncoding("UTF-8");
        String firstPlayerName = request.getParameter("firstplayer");
        String secondPlayerName = request.getParameter("secondplayer");
        try {
            validate(firstPlayerName, secondPlayerName);
        } catch (InvalidNameException ex) {
            response.sendError(response.SC_BAD_REQUEST, ex.getLocalizedMessage());
        }
        game = new Game(firstPlayerName, secondPlayerName);
        game.setWinner("");
        print(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        request.setCharacterEncoding("UTF-8");
        game.move(request.getParameter("button"));
        print(request, response);
    }
}
