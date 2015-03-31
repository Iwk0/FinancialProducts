package bg.financialproducts.util;

import android.content.res.Resources;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import bg.financialproducts.model.Consumer;
import bg.financialproducts.model.Loan;

public class XMLParser {

    public static List<Loan> parse(Resources resources, String defaultValue, int fileId) {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(-1, defaultValue));

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(resources.openRawResource(fileId));

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("option");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    loans.add(new Loan(
                            Integer.parseInt(element.getAttribute("value")),
                            element.getTextContent()));
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        return loans;
    }

    public static List<Consumer> parseConsumers(InputStream content) throws ParserConfigurationException, IOException, SAXException {
        String rawXml = IOUtils.toString(content, "UTF-8");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(rawXml.getBytes())));

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("row");
        List<Consumer> consumers = new ArrayList<>();

        int NODE_LIST_SIZE = nodeList.getLength();
        for (int temp = 0; temp < NODE_LIST_SIZE; temp++) {
            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Consumer consumer = new Consumer();

                final int ELEMENTS_SIZE = element.getElementsByTagName("val").getLength();
                for (int i = 0; i < ELEMENTS_SIZE; i++) {
                    Node tmp = element.getElementsByTagName("val").item(i);
                    String attribute = tmp.getAttributes().item(0).getTextContent();
                    String text = tmp.getTextContent();

                    switch (attribute) {
                        case Constants.PRODUCT:
                            consumer.product = text;
                            break;
                        case Constants.APR:
                            consumer.apr = text;
                            break;
                        case Constants.BANK:
                            consumer.bank = text;
                            break;
                        case Constants.CURRENCY:
                            consumer.currency = text;
                            break;
                        case Constants.MONTHLY_PAYMENT:
                            consumer.monthlyPayment = text;
                            break;
                        case Constants.TOTAL_PAYED:
                            consumer.totalPayed = text;
                            break;
                        case Constants.INTEREST_RATE_TYPE:
                            consumer.interestRateType = text;
                            break;
                    }
                }

                consumers.add(consumer);
            }
        }

        return consumers;
    }
}