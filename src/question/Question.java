package question;

public abstract class Question implements QuestionInterface {
    private QuestionType questionType;
    private String prompt;
    private String answer;

    //Default constructor
    public Question(){

    }

    public Question(QuestionType type, String prompt, String answer){
        this.questionType = type;
        this.prompt = prompt;
        this.answer = answer;
    }

    public QuestionType getQuestionType(){
        return this.questionType;
    }

    public String getQuestionPrompt(){
        return this.prompt;
    }

    public String getQuestionAnswer(){
        return this.answer;
    }
}
