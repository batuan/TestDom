
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;
import org.joox.Match;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.joox.JOOX.$;

public class CreateDom {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        String url = "https://vnexpress.net/trump-don-thuong-doc-ma-giua-tam-bao-4109491.html";
        Document doc = Jsoup.connect(url).get();
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        String xml = doc.html();

        File inputFile = new File("C:\\Users\\ThaiTuan\\IdeaProjects\\TestDom\\src\\xml.txt");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        org.w3c.dom.Document document = factory.newDocumentBuilder().parse(inputFile);
        W3CDom w3CDom = new  W3CDom();
        org.w3c.dom.Document documentPromJsoup = w3CDom.fromJsoup(doc);
        document.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/class/student[2]/firstname";
        String expressionJsoup = "/html/body/section[4]/div/div[2]/article/p[3]";
        NodeList nodeList  = (NodeList) xPath.compile(expressionJsoup).evaluate(document, XPathConstants.NODESET);
        org.w3c.dom.Element newElement = (org.w3c.dom.Element) nodeList.item(0);

        Match x7 = $(document).xpath(expressionJsoup);

        System.out.println($(newElement).xpath());

        System.out.println(document.getElementsByTagName("article").getLength());
        //System.out.println(doc);
    }

}
