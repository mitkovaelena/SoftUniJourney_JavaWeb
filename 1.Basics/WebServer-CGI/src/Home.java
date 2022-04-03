public class Home {
    public static void main(String[] args) {
        StringBuilder headers = new StringBuilder();
        headers.append("Content-type: text/html")
                .append(System.lineSeparator());
        StringBuilder output = new StringBuilder();
        output.append("<html>")
                .append(HtmlElements.HEAD_ELEMENT)
                .append("<body>")
                .append(HtmlElements.BACK_TO_MENU_BTN)
                .append("<h1>Home</h1>")
                .append("<h3 id=\"cakes\">Our cakes</h3>")
                .append("<p><strong><em>Cake</em></strong> is a form of <strong><em>sweet desert</em></strong> that is typically baked. " +
                        "In its oldest forms, cakes were modifications of breads, but " +
                        "cakes now cover a wide range of preparations that can be simple" +
                        " or elaborate, and that share features with other desserts such" +
                        " as pastries, meringues, custards, and pies</p>")
                .append("<img src=\"/cake.jpg\" alt=\"<strong><em>cake</em></strong> image\" width=350>")
                .append("<h3 id=\"stores\">Our stores</h3>")
                .append("<p>Our stores are located in 21 cities all over the world. " +
                        "Come and see what we have for you.</p>")
                .append("<img src=\"/cake-store.jpg\" alt=\"cake store image\" width=350>")
                .append("<pre style=\"background-color: #f94f80\">" +
                        "City: HongKong                  City: Salzburg\n" +
                        "Address: ChoCoLad 18            Address: SchokoLeiden 73\n" +
                        "Phone: +78952804429             Phone: +49241432990</pre>")
                .append(HtmlElements.FOOTER_ELEMENT)
                .append("</body>")
                .append("</html>");

        System.out.println(headers.toString());
        System.out.println(output.toString());
    }
}
