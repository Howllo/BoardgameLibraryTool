package DataParsing;

import Backend.User.Review;
import Backend.User.UserData;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDataParser {
    private Document xmlDocumentTree;
    private final String fileLocation;

    /**
     * Constructor that create xmlDocument for get user.
     * @param fileLocation Takes in a file location to locate the correct file.
     * @throws FileNotFoundException If fail does not exist.
     * @throws IOException If IO cannot access, write, or find it any longer.
     */
    public UserDataParser(String fileLocation) throws FileNotFoundException, IOException {
        File inputFile = new File(fileLocation);
        this.fileLocation = fileLocation;
        if(!inputFile.exists()) return;

        DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
        dbF.setNamespaceAware(true);
        dbF.setExpandEntityReferences(false);

        try{
            DocumentBuilder db = dbF.newDocumentBuilder();
            xmlDocumentTree = db.parse(fileLocation);
        } catch (Exception ex){
            throw new java.io.IOException("Unable to parse XML document. " + ex.getMessage());
        }
    }

    /**
     * Read file from directory and create a new user object.
     * @return a UserData object containing username, password, and all the reviews.
     */
    public UserData getUser(String inUsername, char[] inPassword){
        File checkFileLocation = new File(fileLocation);
        if(!checkFileLocation.exists()){
            System.out.println("Error! Cannot find the file \n" + fileLocation);
            return null;
        }

        String userName = "temporary";
        char[] userPassword = {'t','e','m','p','o','r','a','r','y'};
        String getUserid = "";
        ArrayList<Review> userReviews = new ArrayList<>();

        // I rather be writing this in C#. Java is awful. Worst code I have ever designed, but it works.
        Element user = xmlDocumentTree.getDocumentElement();
        NodeList xmlList = user.getElementsByTagName("username");
        Node nextSibling = xmlList.item(0);

        while(nextSibling.getNextSibling() != null){
            if(nextSibling.getNodeName().equals("username")){
                userName = nextSibling.getFirstChild().getNodeValue();
            }
            else if(nextSibling.getNodeName().equals("userID")){
                getUserid = nextSibling.getFirstChild().getNodeValue();
            } else if(nextSibling.getNodeName().equals("password")){
                userPassword = nextSibling.getFirstChild().getNodeValue().toCharArray();
            } else if(nextSibling.getNodeName().equals("review")){
                 userReviews.add(getReviewFromNode(nextSibling, Integer.parseInt(getUserid)));
            }
            nextSibling = nextSibling.getNextSibling();
        }

        if(userName.equals(inUsername) && Arrays.equals(userPassword, inPassword)){
            return new UserData(userName, userPassword, Integer.parseInt(getUserid), userReviews);
        }
        return null;
    }

    /**
     * Used to get the review from the XML node system and returns the review object.
     * @param reviewNode Take in Node object to process the review data.
     * @param userId Take in the user ID to be used for the review object.
     * @return a Review object to be store in User Data Object.
     */
    private Review getReviewFromNode(Node reviewNode, Integer userId){
        Integer score = 0;
        String reviewText = "";
        String reviewGameID = "";

        NodeList field = reviewNode.getChildNodes();

        for(int i = 0; i < field.getLength(); i++){
            Node currentField = field.item(i);

            if(currentField.getNodeName().equals("playerID")){
                userId = Integer.valueOf(currentField.getFirstChild().getNodeValue());
            } else if(currentField.getNodeName().equals("reviewScore")){
                score = Integer.valueOf(currentField.getFirstChild().getNodeValue());
            } else if(currentField.getNodeName().equals("reviewText")){
                reviewText = currentField.getFirstChild().getNodeValue();
            } else if(currentField.getNodeName().equals("reviewGameID")){
                reviewGameID = currentField.getFirstChild().getNodeValue();
            }
        }

        return new Review(userId, score, reviewText, reviewGameID);
    }

    /**
     * Create processes user data object into a serialized XML file within user_data.
     * @param data Takes in user data object be processed.
     * @return boolean whether it was successful or not at create the XML file.
     * @throws ParserConfigurationException Error
     * @throws TransformerException Error
     * @throws FileNotFoundException Error
     */
    public boolean createUserXML(UserData data) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Root Element
        Element rootElement = doc.createElement("user");
        rootElement.setAttribute("user-type", data.userID.toString());
        doc.appendChild(rootElement);

        // Username
        Element username = doc.createElement("username");
        username.setTextContent(data.userName);
        rootElement.appendChild(username);

        // Password
        Element password = doc.createElement("password");
        password.setTextContent(new String(data.password));
        rootElement.appendChild(password);

        // User ID
        Element userID = doc.createElement("userID");
        userID.setTextContent(data.userID.toString());
        rootElement.appendChild(userID);

        // Reviews
        if(data.userReviews.size() > 0){
            for(Review review : data.userReviews){
                Element reviewElement = doc.createElement("review");
                reviewElement.setAttribute("type", review.getGameID());
                rootElement.appendChild(reviewElement);

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
                Element reviewGameTitle = doc.createElement("gameTitle");
                reviewGameTitle.setTextContent(review.getTitle());
                reviewElement.appendChild(reviewGameTitle);
            }
        }

        // Write to File
        return writeXMLToFile(doc);
    }

    /**
     * Write XML to file.
     * @param document Takes in a document to be process into file.
     * @throws TransformerException Throws a transport error.
     */
    private boolean writeXMLToFile(Document document) throws TransformerException {
        File inputFile = new File(fileLocation);
        if(!inputFile.exists()){
            try {
                if(inputFile.createNewFile()){
                    System.out.println("File Created!");
                } else {
                    System.out.println("Error! Failed to create new user file.");
                    return false;
                }
            } catch (IOException e) {
                System.out.println("Error writing an XML " + e.getMessage());
                return false;
            }
        }

        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);

            // Create and Write to File
            try (FileWriter fileWriter = new FileWriter(inputFile)){
                StreamResult result = new StreamResult(fileWriter);
                transformer.transform(source, result);
            } catch (IOException e){
                System.out.println("IO Error with File Writer: " + e.getMessage());
                return false;
            }
        } catch (TransformerConfigurationException tce){
            System.out.println("Transformer Error: " + tce.getMessage());
            return false;
        }
        return true;
    }
}