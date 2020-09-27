import question.QuestionType;

import java.util.Random;

public class Student implements StudentInterface {

    private int ID;
    private VotingService service;
    private String answer;

    public Student(VotingService service){
        this.ID = this.hashCode();
        this.service = service;
    }

    /**
     * Generates answers based on the conditions of the question type.
     */
    public void generateAnswers() {
        //Initialize empty string
        String generatedAnswer = "";
        //Gets question type(ABCD or Right/Wrong)
        QuestionType questionType = service.getQuestionType();
        //Tells us whether question is single choice or multiple choice(true if single, false if multiple)
        boolean isSingleChoice = service.questionSingleChoice();
        //Generate answer based on question type
        switch(questionType){
            case ABCD:
                if(isSingleChoice){
                    String[] bank = {"A", "B", "C", "D"};
                    Random rand = new Random();
                    int randomIndex = rand.nextInt(3);
                    generatedAnswer = generatedAnswer + bank[randomIndex];
                }else{
                    Random rand = new Random();
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "A" : "");
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "B" : "");
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "C" : "");
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "D" : "");
                }
                break;
            case TRUE_FALSE:
                Random rand = new Random();
                generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "Right" : "Wrong");
                break;
        }
        this.answer = generatedAnswer;
    }

    /**
     * Submits answers by calling methods in VotingService
     */
    public void submitAnswers() {
        //Submit the first generated answer
        service.acceptAnswer(this, this.answer);
        //Create a random object so the probability that they resubmit is 10%
        Random rand = new Random();
        //Create a random number 0-9. Each number has an equal probability of being selected, which is 10%.
        int randNum = rand.nextInt(9);
        //We can choose any number to compare randNum to, as long as it is a single number. In this case, 1 is chosen.
        if(randNum == 1){
            //Re-generate answers
            generateAnswers();
            //Have the voting service accept the new answer.
            service.acceptAnswer(this, this.answer);
        }
    }

    /**
     * Gets student ID.
     *
     * @return the ID of the student
     */
    public int getID() {
        return this.ID;
    }
}
