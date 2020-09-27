package question;

public abstract class Question implements QuestionInterface {
    private QuestionType questionType;
    private String prompt;
    private String answer;

    //Default constructor
    public Question(){
        //Nothing here, but we still need a default constructor
    }

    public Question(QuestionType type, String prompt, String answer){
        this.questionType = type;
        this.prompt = prompt;
        this.answer = answer;
    }

    /**
     * Gets the question type
     * @return the question type
     */
    public QuestionType getQuestionType(){
        return this.questionType;
    }

    /**
     * Gets the question prompt
     * @return the question prompt
     */
    public String getQuestionPrompt(){
        return this.prompt;
    }

    /**
     * Gets the question answer
     * @return the question answer
     */
    public String getQuestionAnswer(){
        return this.answer;
    }
}
