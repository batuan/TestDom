import org.apache.xerces.parsers.DOMParser;
import org.joox.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.cyberneko.html.HTMLConfiguration;
import org.cyberneko.html.HTMLElements;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.joox.JOOX.$;

public class ParserApache {
    private static String USER_AGENT = "Mozilla/5.0 (compatible; BoxBrowserTest/4.x; Linux) CSSBox/4.x (like Gecko)";

    public static void main(String[] args) throws IOException, SAXException, XPathExpressionException {
        String url = "https://vnexpress.net/trump-don-thuong-doc-ma-giua-tam-bao-4109491.html";
        DOMParser domParser = new DOMParser(new HTMLConfiguration());
        domParser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
        URL mURL = new URL(url);
        URLConnection connection = mURL.openConnection();
        connection.setRequestProperty("User-Agent", USER_AGENT);
        domParser.parse(new InputSource(connection.getInputStream()));
        Document document = domParser.getDocument();
        document.getDocumentElement().normalize();
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expressionJsoup = "/html[1]/body[1]/section[4]/div[1]/div[2]/article[1]/figure[1]/figcaption[1]/p[1]";
        NodeList nodeList  = (NodeList) xPath.compile(expressionJsoup).evaluate(document, XPathConstants.NODESET);
        org.w3c.dom.Element newElement = (org.w3c.dom.Element) nodeList.item(0);
        NodeList list = document.getElementsByTagName("article");
        Element pElement = (Element) list.item(0);
        Element mElement = (Element) pElement.getElementsByTagName("p").item(4);
//        System.out.println(mElement.getTextContent());
//        System.out.println($(mElement).xpath());
        System.out.println(newElement.getTextContent());
    }
}
