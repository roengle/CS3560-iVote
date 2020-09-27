package question;

public class SingleChoiceQuestion extends Question{

    /**
     * Constructs a single choice question
     * @param type the type of question(ABCD or TRUE_FALSE)
     * @param prompt the question prompt
     * @param answer the question answer
     */
    public SingleChoiceQuestion(QuestionType type, String prompt, String answer){
        super(type, prompt, answer);
    }
}
