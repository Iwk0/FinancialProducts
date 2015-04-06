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

import bg.financialproducts.model.Auto;
import bg.financialproducts.model.Consumer;
import bg.financialproducts.model.CreditCard;
import bg.financialproducts.model.Deposits;
import bg.financialproducts.model.Loan;
import bg.financialproducts.model.Mortgage;

public class XMLParser {

    public static List<Loan> parse(Resources resources, String defaultValue, int fileId) {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan("-1", defaultValue));

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
                            element.getAttribute("value"),
                            element.getTextContent()));
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        return loans;
    }

    private static NodeList getElementByTagName(InputStream content) throws ParserConfigurationException,
            IOException, SAXException {
        String rawXml = IOUtils.toString(content, "UTF-8").replaceAll("&", "&amp;");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(rawXml.getBytes())));

        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName("row");
    }

    public static List<Consumer> parseConsumers(InputStream content) throws ParserConfigurationException,
            IOException, SAXException {
        NodeList nodeList = getElementByTagName(content);
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

    public static List<Mortgage> parseMortgage(InputStream content) throws IOException, ParserConfigurationException, SAXException {
        NodeList nodeList = getElementByTagName(content);
        List<Mortgage> mortgages = new ArrayList<>();

        int NODE_LIST_SIZE = nodeList.getLength();
        for (int temp = 0; temp < NODE_LIST_SIZE; temp++) {
            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Mortgage mortgage = new Mortgage();

                final int ELEMENTS_SIZE = element.getElementsByTagName("val").getLength();
                for (int i = 0; i < ELEMENTS_SIZE; i++) {
                    Node tmp = element.getElementsByTagName("val").item(i);
                    String attribute = tmp.getAttributes().item(0).getTextContent();
                    String text = tmp.getTextContent();

                    switch (attribute) {
                        case Constants.PRODUCT:
                            mortgage.product = text;
                            break;
                        case Constants.APR:
                            mortgage.apr = text;
                            break;
                        case Constants.BANK:
                            mortgage.bank = text;
                            break;
                        case Constants.CURRENCY:
                            mortgage.currency = text;
                            break;
                        case Constants.MONTHLY_PAYMENT:
                            mortgage.monthlyPayment = text;
                            break;
                        case Constants.TOTAL_PAYED:
                            mortgage.totalPayed = text;
                            break;
                        case Constants.ML_INTEREST_TYPE:
                            mortgage.interestType = text;
                            break;
                        case Constants.DOWN_PAYMENT:
                            mortgage.downPayment = text;
                            break;
                    }
                }

                mortgages.add(mortgage);
            }
        }

        return mortgages;
    }

    public static List<Auto> parseAuto(InputStream content) throws IOException,
            ParserConfigurationException, SAXException {
        NodeList nodeList = getElementByTagName(content);
        List<Auto> autos = new ArrayList<>();

        int NODE_LIST_SIZE = nodeList.getLength();
        for (int temp = 0; temp < NODE_LIST_SIZE; temp++) {
            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Auto auto = new Auto();

                final int ELEMENTS_SIZE = element.getElementsByTagName("val").getLength();
                for (int i = 0; i < ELEMENTS_SIZE; i++) {
                    Node tmp = element.getElementsByTagName("val").item(i);
                    String attribute = tmp.getAttributes().item(0).getTextContent();
                    String text = tmp.getTextContent();

                    switch (attribute) {
                        case Constants.PRODUCT:
                            auto.product = text;
                            break;
                        case Constants.APR:
                            auto.APR = text;
                            break;
                        case Constants.BANK:
                            auto.bank = text;
                            break;
                        case Constants.CURRENCY:
                            auto.currency = text;
                            break;
                        case Constants.MONTHLY_PAYMENT:
                            auto.monthlyPayment = text;
                            break;
                        case Constants.TOTAL_PAYED:
                            auto.totalPayed = text;
                            break;
                        case Constants.MIN_SELF_PARTICIPATION:
                            auto.minSelfParticipation = text;
                            break;
                    }
                }

                autos.add(auto);
            }
        }

        return autos;
    }

    public static List<CreditCard> parseCreditCards(InputStream content) throws IOException,
            ParserConfigurationException, SAXException {
        NodeList nodeList = getElementByTagName(content);
        List<CreditCard> creditCards = new ArrayList<>();

        int NODE_LIST_SIZE = nodeList.getLength();
        for (int temp = 0; temp < NODE_LIST_SIZE; temp++) {
            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                CreditCard creditCard = new CreditCard();

                final int ELEMENTS_SIZE = element.getElementsByTagName("val").getLength();
                for (int i = 0; i < ELEMENTS_SIZE; i++) {
                    Node tmp = element.getElementsByTagName("val").item(i);
                    String attribute = tmp.getAttributes().item(0).getTextContent();
                    String text = tmp.getTextContent();

                    switch (attribute) {
                        case Constants.PRODUCT:
                            creditCard.product = text;
                            break;
                        case Constants.ANNUAL_FEE_MAIN:
                            creditCard.annualFeeMain = text;
                            break;
                        case Constants.CASH_APR:
                            creditCard.cashAPR = text;
                            break;
                        case Constants.CASH_RATE:
                            creditCard.cashRate = text;
                            break;
                        case Constants.CREDIT_CARD_LIMIT:
                            creditCard.creditCardLimit = text;
                            break;
                        case Constants.PURCHASE_RATE:
                            creditCard.purchaseRate = text;
                            break;
                        case Constants.BANK:
                            creditCard.bank = text;
                            break;
                    }
                }

                creditCards.add(creditCard);
            }
        }

        return creditCards;
    }

    public static List<Deposits> parseDeposits(InputStream content) throws IOException,
            ParserConfigurationException, SAXException {
        NodeList nodeList = getElementByTagName(content);
        List<Deposits> deposits = new ArrayList<>();

        int NODE_LIST_SIZE = nodeList.getLength();
        for (int temp = 0; temp < NODE_LIST_SIZE; temp++) {
            Node node = nodeList.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Deposits deposit = new Deposits();

                final int ELEMENTS_SIZE = element.getElementsByTagName("val").getLength();
                for (int i = 0; i < ELEMENTS_SIZE; i++) {
                    Node tmp = element.getElementsByTagName("val").item(i);
                    String attribute = tmp.getAttributes().item(0).getTextContent();
                    String text = tmp.getTextContent();

                    switch (attribute) {
                        case Constants.PRODUCT:
                            deposit.product = text;
                            break;
                        case Constants.AER:
                            deposit.AER = text;
                            break;
                        case Constants.AFTER_REVENUE_TAX_AMOUNT:
                            deposit.afterRevenueTaxAmount = text;
                            break;
                        case Constants.INTEREST_RATE_TYPE:
                            deposit.interestRateType = text;
                            break;
                        case Constants.BANK:
                            deposit.bank = text;
                            break;
                    }
                }

                deposits.add(deposit);
            }
        }

        return deposits;
    }
}