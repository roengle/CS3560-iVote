import question.QuestionType;

import java.util.Random;

public class Student implements StudentInterface {

    //Instance fields
    private int ID;
    private VotingService service;
    private String answer;

    /**
     * Constructs a Student object
     * @param service the {@link VotingService} service
     */
    public Student(VotingService service){
        //Set Student ID the the instances hash code--easy solution for ID generation
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
                //If question is single-choice
                if(isSingleChoice){
                    //Create a bank of possible answers
                    String[] bank = {"A", "B", "C", "D"};
                    //Create a new random object
                    Random rand = new Random();
                    //Get a random number(0 - 3) which will represent the index for the bank
                    int randomIndex = rand.nextInt(3);
                    //Set the answer to a random selection from the bank
                    generatedAnswer = generatedAnswer + bank[randomIndex];
                }
                //If question is multiple-choice
                else{
                    //Create new random object
                    Random rand = new Random();
                    //Add random answers to the answer string. Each letter has a 50% chance of being added.
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "A" : "");
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "B" : "");
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "C" : "");
                    generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "D" : "");
                }
                break;
            case TRUE_FALSE:
                //Create a new random object
                Random rand = new Random();
                //Set the answer to either Right or Wrong based on the random choice
                generatedAnswer = generatedAnswer + (rand.nextBoolean() ? "Right" : "Wrong");
                break;
        }
        //Set the answer to our generated answer
        this.answer = generatedAnswer;
    }

    /**
     * Submits answers by calling method in VotingService
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
