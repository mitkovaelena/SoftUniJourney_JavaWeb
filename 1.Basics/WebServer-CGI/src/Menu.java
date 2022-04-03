public class Menu {
    public static void main(String[] args) {
        StringBuilder headers = new StringBuilder();
        headers.append("Content-type: text/html")
                .append(System.lineSeparator());
        StringBuilder output = new StringBuilder();
        output.append("<html>")
                .append(HtmlElements.HEAD_ELEMENT)
                .append("<body>")
                .append("<h1>By The Cake</h1>")
                .append("<h2>Enjoy our awesome cakes</h2>")
                .append("<hr/>")
                .append("<ul>")
                .append("<li><a href=\"/cgi-bin/home.cgi\">Home</a>")
                .append("<ol>")
                .append("<li><a href=\"/cgi-bin/home.cgi#cakes\">Our cakes</a></li>")
                .append("<li><a href=\"/cgi-bin/home.cgi#stores\">Our stores</a></li>")
                .append("</ol></li>")
                .append("<li><a href=\"/cgi-bin/add-cake.cgi\">Add cake</a></li>")
                .append("<li><a href=\"/cgi-bin/browse-cakes.cgi\">Browse cakes<a/></li>")
                .append("<li><a href=\"/cgi-bin/about-us.cgi\">About us</a></li>")
                .append("</ul>")
                .append(HtmlElements.FOOTER_ELEMENT)
                .append("</body>")
                .append("</html>");

        System.out.println(headers.toString());
        System.out.println(output.toString());
    }
}
