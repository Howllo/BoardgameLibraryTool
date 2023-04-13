package DataParsing;

import Backend.Database.Database;
import Backend.User.Review;
import Backend.User.UserData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void createUserXML(UserData data) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Root Element
        Element rootElement = doc.createElement("username");
        rootElement.setTextContent(data.userName);
        doc.appendChild(rootElement);

        // User ID
        Element userID = doc.createElement("userID");
        userID.setTextContent(data.userID.toString());
        rootElement.appendChild(userID);

        // Password
        Element password = doc.createElement("password");
        password.setTextContent(Arrays.toString(data.password));
        rootElement.appendChild(password);

        // Create the Review Element
        for(Review review : data.userReviews){
            Element reviewElement = doc.createElement("review");
            reviewElement.setTextContent(review.getGameID());

            // Player ID - Review
            Element playerID = doc.createElement("playerID");
            playerID.setTextContent(review.getPlayerID().toString());
            reviewElement.appendChild(playerID);

            // Player Score - Review
            Element reviewScore = doc.createElement("reviewScore");
            reviewScore.setTextContent(review.getPlayerScore().toString());
            reviewElement.appendChild(reviewScore);

            // Player Review Text - Review
            Element reviewText =doc.createElement("reviewText");
            reviewText.setTextContent(review.getReviewText());
            reviewElement.appendChild(reviewText);

            // Review Game ID - Review
            Element reviewGameID = doc.createElement("reviewGameID");
            reviewGameID.setTextContent(review.getGameID());
            reviewElement.appendChild(reviewGameID);

            // Review Game Title - Review
            Element reviewGameTitle = doc.createElement("gameTitel");
            reviewGameTitle.setTextContent(review.getTitle());
            reviewElement.appendChild(reviewGameTitle);
        }

        // Create the File
        writeXMLToFile(doc);
    }

    private void writeXMLToFile(Document document){
        String gameDataPath = Database.getInstance().getGameDataPath();


    }
}