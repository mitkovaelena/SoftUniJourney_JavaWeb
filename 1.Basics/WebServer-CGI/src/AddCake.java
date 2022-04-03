import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddCake {
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
                .append("<form method=\"post\" action=\"/cgi-bin/add-cake.cgi\">\n")
                .append("<div>\n")
                .append("<label for=\"name\">Name:</label>\n")
                .append("<input id=\"name\" name=\"name\" type=\"text\"/>\n")
                .append("</div>\n")
                .append("<div>\n")
                .append("<label for=\"price\">Price:</label>\n")
                .append("<input id=\"price\" name=\"price\" type=\"number\" step=\"0.01\"/>\n")
                .append("</div>\n")
                .append("<div>\n")
                .append("<input type=\"submit\" name=\"add_cake_btn\" value=\"Create Cake\"/>\n")
                .append("</div>\n")
                .append("</form>");

        if (System.getenv("REQUEST_METHOD").equals("POST")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Map<String, String> postParams = getParams(reader.readLine());
            if(postParams.containsKey("add_cake_btn")){
                String name = postParams.get("name");
                BigDecimal price = new BigDecimal(postParams.get("price"));
                Cake cake = new Cake(name,price);

                if(saveInDbFile(cake)){
                    output.append("<div>name: ")
                            .append(name)
                            .append("</div>")
                            .append("<div>price:")
                            .append(price)
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

    private static boolean saveInDbFile(Cake cake){
        try(FileWriter fileWriter = new FileWriter("database.cvs", true)) {
            fileWriter.append(cake.getName().replace("+", " "))
                    .append(",")
                    .append(String.valueOf(cake.getPrice()))
                    .append(System.lineSeparator());
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
