package question;

public interface QuestionInterface {

    /**
     * Gets the question type
     * @return the question type
     */
    public QuestionType getQuestionType();

    /**
     * Gets the question prompt
     * @return the question prompt
     */
    public String getQuestionPrompt();

    /**
     * Gets the question answer
     * @return the question answer
     */
    public String getQuestionAnswer();
}
