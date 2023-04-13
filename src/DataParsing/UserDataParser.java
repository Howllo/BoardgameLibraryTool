package DataParsing;

import Backend.User.Review;
import Backend.User.UserData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

public class UserDataParser {
    private Document xmlDocumentTree;

    UserDataParser(String fileLocation) throws FileNotFoundException, IOException {
         File inputFile = new File(fileLocation);
         if(!inputFile.exists()) return;

        DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
        dbF.setNamespaceAware(true);
        dbF.setExpandEntityReferences(false);

        try{
            DocumentBuilder db = dbF.newDocumentBuilder();
            xmlDocumentTree = db.parse(fileLocation);
        } catch (Exception ex){
            throw new java.io.IOException("Unable to parse XML document.");
        }
    }

    /**
     * Read file from directory and create a new user object.
     * @return a UserData object containing username, password, and all the reviews.
     */
    public UserData getUser(){
        String userName = "admin";
        char[] userPassword = {'a', 'd', 'm', 'i', 'n'};
        ArrayList<Review> userReviews = new ArrayList<>();



        return new UserData(userName, userPassword, userReviews);
    }

    public void CreateUserObject(UserData data, String fileLocation) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        // Root Element
        Element rootElement = doc.createElement("username");
        rootElement.setTextContent(data.userName);
        doc.appendChild(rootElement);

        // Child Elements
        //Element
    }
}
