package question;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String prompt, String answer){
        super(QuestionType.ABCD, prompt, answer);
    }
}
