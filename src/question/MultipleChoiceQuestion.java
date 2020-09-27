package question;

public class MultipleChoiceQuestion extends Question {
    /**
     * Constructs a multiple choice question
     * @param prompt the prompt for the question
     * @param answer the answer(s) for the question
     */
    public MultipleChoiceQuestion(String prompt, String answer){
        //Call super constructor, but since it is multiple choice we cannot have True/False questions.
        super(QuestionType.ABCD, prompt, answer);
    }
}
