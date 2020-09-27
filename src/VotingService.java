import question.Question;
import question.QuestionType;
import question.SingleChoiceQuestion;

import java.util.HashMap;
import java.util.Iterator;

public class VotingService implements VotingServiceInterface{

    /*Instance Fields*/
    //Keeps track of how many students voted for each answer
    private HashMap<String, Integer> questionStatistics;
    //Keeps track of the answers of individual students
    private HashMap<Integer, String> studentAnswers;
    //The current question in the service
    private Question question;

    /**
     * Construct a VotingService object
     */
    public VotingService(){
        //Initialize both hash maps
        questionStatistics = new HashMap<>();
        studentAnswers = new HashMap<>();
    }
    /**
     * Configures the voting service with a Question
     *
     * @param question the question to configure the voting service with
     */
    public void configureQuestion(Question question) {
        this.question = question;
    }

    /**
     * Accepts an answer from a student. Is called by Student objects
     * after they generate an answer.
     *
     * @param student the student to accept the answer from
     * @param answer  the student's answer
     */
    public void acceptAnswer(Student student, String answer) {
        //There is already an answer for this student
        if(studentAnswers.containsKey(student.getID())){
            studentAnswers.put(student.getID(), answer);
            String outputLine = String.format("%s", "Student " + student.getID() + " has resubmitted answer:" + answer
                            + ", overriding old answer - " + (question.getQuestionAnswer().equals(answer) ? "CORRECT" : "INCORRECT"));
            System.out.println(outputLine);
        }
        //There is NOT a previous answer for this student
        else{
            studentAnswers.put(student.getID(), answer);
            System.out.println("Student " + student.getID() + " has submitted answer:" + answer + " - " + (question.getQuestionAnswer().equals(answer) ? "CORRECT" : "INCORRECT"));
        }
    }

    /**
     * Outputs statistics on how many people chose various options.
     */
    public void outputStatistics() {
        //Create an iterator for the student answer hash map
        Iterator studentAnswerIterator = studentAnswers.keySet().iterator();
        //Output statistics based on the question type
        switch(question.getQuestionType()){
            case ABCD:
                //Initialize values for A through D
                questionStatistics.put("A", 0);
                questionStatistics.put("B", 0);
                questionStatistics.put("C", 0);
                questionStatistics.put("D", 0);
                //Fill questionStatistics by iterating through each student and their answers
                while(studentAnswerIterator.hasNext()){
                    //Get the current key
                    Integer studentID = (Integer)studentAnswerIterator.next();
                    //Get the current key's value
                    String studentAnswer = studentAnswers.get(studentID);
                    //Loop through the student's answer to determine which choices they selected
                    for(int i = 0; i < studentAnswer.length(); i++){
                        //Get each letter in the answer
                        String letter = Character.toString(studentAnswer.charAt(i));
                        //Get the current count of said letter
                        int questionStatisticsValue = questionStatistics.get(letter);
                        //Increment that letter's value
                        questionStatistics.put(letter, (questionStatisticsValue + 1));
                    }
                }
                break;
            case TRUE_FALSE:
                //Initialize values for Right and Wrong
                questionStatistics.put("Right", 0);
                questionStatistics.put("Wrong", 0);
                //Iterate through the student answers
                while(studentAnswerIterator.hasNext()){
                    //Get the key
                    Integer studentID = (Integer)studentAnswerIterator.next();
                    //Get the key's value
                    String studentAnswer = studentAnswers.get(studentID);
                    //Get the answers current count
                    int questionStatisticsValue = questionStatistics.get(studentAnswer);
                    //Increment the count
                    questionStatistics.put(studentAnswer, questionStatisticsValue + 1);
                }
                break;
        }

        //Print out statistics for each letter
        System.out.println("\nQUESTION STATISTICS:");
        //Initialize iterator for the question statistics hash map
        Iterator questionStatisticsIterator = questionStatistics.keySet().iterator();
        //Loop through question statistics hash map
        while (questionStatisticsIterator.hasNext()) {
            //Get the key
            String letter = (String)questionStatisticsIterator.next();
            //Get the key's value
            int amount = questionStatistics.get(letter);
            //Print out key:value pair
            System.out.println(letter + ": " + amount);
        }
    }

    /**
     * Gets the question type.
     * @return the question type
     */
    public QuestionType getQuestionType(){
        return question.getQuestionType();
    }

    /**
     * Checks if the question is single choice or multiple choice
     * @return true if single choice, false if multiple choice
     */
    public boolean questionSingleChoice(){
        return question instanceof SingleChoiceQuestion;
    }

    /**
     * Clears the associated data of the voting service
     */
    public void clear() {
        //Clear old data for hash maps
        questionStatistics = new HashMap<>();
        studentAnswers = new HashMap<>();
        //Clear current question
        question = null;
    }
}
