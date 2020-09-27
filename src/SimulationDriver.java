import question.MultipleChoiceQuestion;
import question.Question;
import question.QuestionType;
import question.SingleChoiceQuestion;

import java.util.Random;

public class SimulationDriver {
    public static void main(String[] args){
        //Make a new voting service
        VotingService mainService = new VotingService();
        //Generate random list of students
        Random rand = new Random();
        //Generate a random amount of students between 15 and 30
        int numStudents = 15 + rand.nextInt(15);
        //Create an array to keep track of all of our students
        Student[] studentArray = new Student[numStudents];
        //Initialize every student in the array
        for(int i = 0; i < numStudents; i++){
            studentArray[i] = new Student(mainService);
        }
        //Print number of students
        System.out.println("Number of students: " + numStudents);

        //Make an example question that is single choice, options A, B, C, and D
        String questionPrompt = "(Single Choice ABCD Test) What is 2 + 2?\n" +
                "A)1 \n" +
                "B)2 \n" +
                "C)4 \n" +
                "D)9 \n";
        String questionAnswer = "C";
        Question singleChoiceEx = new SingleChoiceQuestion(QuestionType.ABCD, questionPrompt, questionAnswer);

        //Configure service with question
        mainService.configureQuestion(singleChoiceEx);


        //Print question
        System.out.println("Question: " + singleChoiceEx.getQuestionPrompt());
        System.out.println("Correct Answer: " + questionAnswer);
        //Loop through student array to generate their answers and submit those to the service
        for(Student s : studentArray){
            s.generateAnswers();
            s.submitAnswers();
        }
        //Output statistics
        mainService.outputStatistics();
        System.out.println();

        /*---------------------------------TRUE/FALSE TEST-----------------------------*/
        //Clear service of previous question
        mainService.clear();
        questionPrompt = "(Single Choice Right/Wrong Test) The integral of x^2 is (x^3)/3?\n" +
                "Right \n" +
                "Wrong \n";
        questionAnswer = "Right";
        Question trueFalseEx = new SingleChoiceQuestion(QuestionType.TRUE_FALSE, questionPrompt, questionAnswer);

        //Configure question
        mainService.configureQuestion(trueFalseEx);

        System.out.println("Question: " + trueFalseEx.getQuestionPrompt());
        System.out.println("Correct Answer: " + questionAnswer);
        for(Student s : studentArray){
            s.generateAnswers();
            s.submitAnswers();
        }
        //Output statistics
        mainService.outputStatistics();
        System.out.println();

        /*---------------------------------MULTIPLE CHOICE TEST-----------------------------*/
        //Clear service of previous question
        mainService.clear();
        questionPrompt = "(Multiple Choice Test) Which ones are integers?\n" +
                "A) 1 \n" +
                "B) 2 \n" +
                "C) 3.5 \n" +
                "D) -0.5 \n";
        questionAnswer = "AB";
        Question multipleChoiceEx = new MultipleChoiceQuestion(questionPrompt, questionAnswer);

        //Configure question
        mainService.configureQuestion(multipleChoiceEx);

        System.out.println("Question: " + multipleChoiceEx.getQuestionPrompt());
        System.out.println("Correct Answer: " + questionAnswer);
        for(Student s : studentArray){
            s.generateAnswers();
            s.submitAnswers();
        }
        //Output statistics
        mainService.outputStatistics();
    }
}
