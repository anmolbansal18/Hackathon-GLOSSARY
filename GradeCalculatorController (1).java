package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeCalculatorController {
	Stage applicationStage;
	double averageRequiredQuizGrade = 0.0;
	double averageOptionalQuizGrade = 0.0;

    @FXML
    private TextField projectGradeTextField;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoiceBox;

    @FXML
    private ChoiceBox<Integer> reqeiredCodingChallengesChoiceBox;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    private Label projectGradeErrorLabel;
    
    @FXML
    private ChoiceBox<Integer> optQuizzesChoiceBox;

    @FXML
    private ChoiceBox<Integer> reqQuizzesChoiceBox;
    
    @FXML
    private Label reqAverageQuizGrade;
    
    @FXML 
    private Label optAverageQuizGrade;
    
    /**
     * Check if the value provided by the user is valid or not. If the value is numeric and a percentage
     * between (0 and 100). If Valid, a double of equivalent value is returned, if not, this method 
     * returns 0.
     * 
     * @param valueEntered - the value entered as the project grade
     * @return the double of valueEntered if it is numeric and valid percentage and 0 otherwise.
     */
    double getProjectGrade(String valueEntered) {
    	
    	// Check that user entered a numeric value
    	boolean validProjectGrade = true;
    	int numDecimalPoints = 0;
    	for (char a : valueEntered.toCharArray()) {
    		// if any character is not a digit, set flag to false: it is not a number
    		// modified to to keep the value of validProjectGrade true when a decimal is entered.
    		// modified to to keep the value of validProjectGrade true when a decimal is entered
    		if(!Character.isDigit(a) && a != '.' && a!='-') {
    		
    			validProjectGrade = false;
    			projectGradeErrorLabel.setText("Don't include the character:  " + a 
    				+	"  Project grade should be percentage  ");
    		}
    		
    	   if (a=='.') {
    		   numDecimalPoints=numDecimalPoints+1;
    	   }
    	   
    	   if (numDecimalPoints>1) {
    		   validProjectGrade = false;
    		   projectGradeErrorLabel.setText("Don't include the character '.' more than once ");
    	   }
    		 
    	    
    	}
    	
    	/* Default project grade is set to 0. If a Valid number is entered by the user covert project
    	 * grade to floating number.
    	*/
    	
    	double projectGrade = 0.0;
    	
    	if (validProjectGrade) {
    	    projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	// Check If the project grade entered by the user is valid. If not reset the default grade to 0.
        if (projectGrade < 0 || projectGrade >100) {
    		
    		projectGradeErrorLabel.setText("Project grade should be between 0% and 100%. Invalid project grade: "
    				+ projectGrade );
    		projectGrade=0.0;
    	}

    	
    	return projectGrade;
    }
    
	double avgOptQuizGrade = 0.0;
    void calculateOptionalQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextFields) {
    	for  (TextField textfield : quizGradeTextFields) {
    		avgOptQuizGrade += 	Double.parseDouble(textfield.getText());
     	   
    	}
    	        avgOptQuizGrade = avgOptQuizGrade / 5;
    	        
    	    	applicationStage.setScene(mainScene);
    	    	optAverageQuizGrade.setText(""+avgOptQuizGrade+"/10");
    	   	
    }
    
    
    
    @FXML
    void getOptionalQuizGrades(ActionEvent enterQuizGradesEvent) {
    	Scene mainScene= applicationStage.getScene();
    	applicationStage.setTitle("Optional Quizzes Calculator");
    	
    	
    	int numberOfQuizzes = optQuizzesChoiceBox.getValue();
    	int rowCounter = 0;
    	VBox allRows = new VBox();
    	ArrayList<TextField> quizTextFields = new ArrayList<TextField>();
    	while(rowCounter<numberOfQuizzes) {
    		rowCounter++;
    		HBox quizRow = new HBox();
    		
        	Label quizLabel = new Label("Quiz " + rowCounter + " grade");
        	TextField quizGradeTextField = new TextField();
        	quizTextFields.add(quizGradeTextField);

        	
        	quizRow.getChildren().addAll(quizLabel,quizGradeTextField);
        	
        	
        	allRows.getChildren().add(quizRow);
        	
        	
        	
    	}
    	Button doneButton = new Button("Done");
    	//This will transfer us to a new pop-up window name"Optional Quiz Grades"
    	doneButton.setOnAction(doneEvent -> calculateOptionalQuizGrade(mainScene,quizTextFields));
    	allRows.getChildren().add(doneButton);
    	
    	Scene quizScene = new Scene(allRows);
    	applicationStage.setScene(quizScene);
    }
    
	double avgReqQuizGrade = 0.0;
    void calculateRequiredQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextFields) {
    	for  (TextField textfield : quizGradeTextFields) {
    		avgReqQuizGrade += 	Double.parseDouble(textfield.getText());
     	   
    	}
    	        avgReqQuizGrade = avgReqQuizGrade / 15;
    	        
    	    	applicationStage.setScene(mainScene);
    	    	reqAverageQuizGrade.setText(""+avgReqQuizGrade+"/10");
    	   	
    }
    
    
    @FXML
    void getRequiredQuizGrades(ActionEvent enterQuizGradesEvent) {
    	Scene mainScene= applicationStage.getScene();
    	applicationStage.setTitle("Required Quiz Calculator");
    	int numberOfQuizzes = reqQuizzesChoiceBox.getValue();
    	int rowCounter = 0;
    	VBox allRows = new VBox();
    	ArrayList<TextField> quizTextFields = new ArrayList<TextField>();
    	while(rowCounter<numberOfQuizzes) {
    		rowCounter++;
    		HBox quizRow = new HBox();
    		
        	Label quizLabel = new Label("Quiz " + rowCounter + " grade");
        	TextField quizGradeTextField = new TextField();
        	quizTextFields.add(quizGradeTextField);

        	
        	quizRow.getChildren().addAll(quizLabel,quizGradeTextField);
        	
        	
        	allRows.getChildren().add(quizRow);
        	
        	
        	
    	}
    	Button doneButton = new Button("Done");
    	//This will transfer us to a new pop-up window name"Required Quiz Grades"
    	doneButton.setOnAction(doneEvent -> calculateRequiredQuizGrade(mainScene,quizTextFields));
    	allRows.getChildren().add(doneButton);
    	
    	Scene quizScene = new Scene(allRows);
    	applicationStage.setScene(quizScene);
    }
   
    /**
     * Compute the grades of all components in a course. The user can enter their grades for different components
     * via a pop-up window. The result of their overall course grade will also be displayed there. 
     * @param event - this method is called when the "Calculate Grade" button is clicked on the pop-up window. 
     */

    @FXML
    void calculateGrade(ActionEvent event) {
    	
    	projectGradeErrorLabel.setText("");
    	double courseGrade = 0.0;
    	
    	String projectValueEntered = projectGradeTextField.getText();
    	
    	    	
        //Check if user entered a percentage grade. If not, display error message
    	double projectGrade = getProjectGrade(projectValueEntered);
    	//and don't include project grade in course grade
    	
        
    	    courseGrade = courseGrade + projectGrade * 0.5;
    	    
    	System.out.println("Project Grade entered: "+ projectGrade +
    			". Course grade so far: " + courseGrade);
    	
    	reqAverageQuizGrade.setText(""+avgReqQuizGrade);
    	optAverageQuizGrade.setText(""+avgOptQuizGrade);
    	
    	courseGrade += avgReqQuizGrade * 1.875;
    	System.out.println("Average Required Quiz Grade:"+ avgReqQuizGrade +". Course Grade so far: " + courseGrade);
    	
    	courseGrade += avgOptQuizGrade * 0.625;
    	System.out.println("Average optional Quiz Grade:"+ avgOptQuizGrade +". Course Grade so far: " + courseGrade);
    	
    	double requiredCodingChallengesPassed= reqeiredCodingChallengesChoiceBox.getValue();
    	courseGrade += requiredCodingChallengesPassed * 1.25;
    	System.out.println("Required Coding Challenges Passed: "+ requiredCodingChallengesPassed +
    			". Course grade so far: " + courseGrade);
    	
    	double optionalCodingChallengesPassed= optionalCodingChallengesChoiceBox.getValue();
    	courseGrade += optionalCodingChallengesPassed * 1.25;
    	System.out.println("Optional Coding Challenges Passed: "+ optionalCodingChallengesPassed +
    			". Course grade so far: " + courseGrade);
    	
    	
    	//Grade Result Display to the User
    	courseGradeLabel.setText(String.format("Your overall course grade: %.2f", courseGrade));
    	
    	
    	
    	
    }

}