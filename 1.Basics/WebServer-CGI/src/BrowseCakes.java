import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class BrowseCakes {
    public static void main(String[] args) throws IOException {
        StringBuilder headers = new StringBuilder();
        headers.append("Content-type: text/html")
                .append(System.lineSeparator());
        System.out.println(headers.toString());
        StringBuilder output = new StringBuilder();
        output.append("<html>")
                .append(HtmlElements.HEAD_ELEMENT)
                .append("<body>")
                .append(HtmlElements.BACK_TO_MENU_BTN)
                .append("<form method=\"get\" action=\"/cgi-bin/browse-cakes.cgi\">\n")
                .append("<div>\n")
                .append("<label for=\"searchValue\">Search:</label>\n")
                .append("<input id=\"searchValue\" name=\"search_value\" type=\"text\"/>\n")
                .append("<input type=\"submit\" name=\"search_btn\" value=\"Search\"/>\n")
                .append("</form>");

        if (System.getenv("REQUEST_METHOD").equals("GET") && !System.getenv("QUERY_STRING").isEmpty()) {
            Map<String, String> params = getParams(System.getenv("QUERY_STRING"));
            if(params.containsKey("search_btn")){
                String value = params.get("search_value");
                for(Cake cake : searchInDbFile(value)){
                    output.append("<div>Name: ")
                            .append(cake.getName())
                            .append(" Price:")
                            .append(cake.getPrice().toString())
                            .append("</div>");
                }
            }
        }

        output.append(HtmlElements.FOOTER_ELEMENT)
                .append("</body>")
                .append("</html>");

        System.out.println(output.toString());
    }

    private static Map<String, String> getParams(String params){
        Map<String, String> requestParams = new HashMap<>();
        for(String pairStr : params.split("&")){
            String[] pair = pairStr.split("=");
            requestParams.put(pair[0], pair[1]);
        }
        return requestParams;
    }

    private static List<Cake> searchInDbFile(String value){
        List<Cake> foundCakes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader( new FileReader("database.cvs"))) {
            String line = null;
            while ((line = br.readLine()) != null){
                if(line.contains(value)){
                    String[] cakeStr = line.split(",");
                    Cake cake = new Cake(cakeStr[0], new BigDecimal(cakeStr[1]));
                    foundCakes.add(cake);
                }
            }
        } catch (IOException ignored) {
        }
        return foundCakes;
    }
}
