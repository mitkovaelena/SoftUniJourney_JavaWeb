public class AboutUs {
    public static void main(String[] args) {
        StringBuilder headers = new StringBuilder();
        headers.append("Content-type: text/html")
                .append(System.lineSeparator());
        StringBuilder output = new StringBuilder();
        output.append("<html>")
                .append(HtmlElements.HEAD_ELEMENT)
                .append("<body>")
                .append(HtmlElements.BACK_TO_MENU_BTN)
                .append("<h2>About us</h2>")
                .append("<dl>")
                .append("<dt>ByTheCake EOOD</dt>")
                .append("<dd>Name of the company</dd>")
                .append("<dt>Elena</dt>")
                .append("<dd>Owner</dd>")
                .append("</dl>")
                .append(HtmlElements.FOOTER_ELEMENT)
                .append("</body>")
                .append("</html>");

        System.out.println(headers.toString());
        System.out.println(output.toString());
    }
}
