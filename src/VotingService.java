import question.Question;
import question.QuestionType;
import question.SingleChoiceQuestion;

import java.util.HashMap;
import java.util.Iterator;

public class VotingService implements VotingServiceInterface{

    private HashMap<String, Integer> questionStatistics;
    private HashMap<Integer, String> studentAnswers;
    private Question question;

    public VotingService(){
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
            String outputLine = String.format("%50s", "Student " + student.getID() + " has resubmitted answer:" + answer
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
        Iterator studentAnswerIterator = studentAnswers.keySet().iterator();
        switch(question.getQuestionType()){
            case ABCD:
                questionStatistics.put("A", 0);
                questionStatistics.put("B", 0);
                questionStatistics.put("C", 0);
                questionStatistics.put("D", 0);
                //Fill questionStatistics by iterating through each student and their answers
                while(studentAnswerIterator.hasNext()){
                    Integer studentID = (Integer)studentAnswerIterator.next();
                    String studentAnswer = studentAnswers.get(studentID);
                    for(int i = 0; i < studentAnswer.length(); i++){
                        String letter = Character.toString(studentAnswer.charAt(i));
                        int questionStatisticsValue = questionStatistics.get(letter);
                        questionStatistics.put(letter, (questionStatisticsValue + 1));
                    }
                }
                break;
            case TRUE_FALSE:
                questionStatistics.put("Right", 0);
                questionStatistics.put("Wrong", 0);
                while(studentAnswerIterator.hasNext()){
                    Integer studentID = (Integer)studentAnswerIterator.next();
                    String studentAnswer = studentAnswers.get(studentID);
                    int questionStatisticsValue = questionStatistics.get(studentAnswer);
                    questionStatistics.put(studentAnswer, questionStatisticsValue + 1);
                }
                break;
        }

        //Print out statistics for each letter
        System.out.println("\nQUESTION STATISTICS:");
        Iterator questionStatisticsIterator = questionStatistics.keySet().iterator();
        while (questionStatisticsIterator.hasNext()) {
            String letter = (String)questionStatisticsIterator.next();
            int amount = questionStatistics.get(letter);
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
