import question.Question;

public interface VotingServiceInterface {

    /**
     * Configures the voting service with a Question
     * @param question the question to configure the voting service with
     */
    public void configureQuestion(Question question);

    /**
     * Accepts an answer from a student. Is called by Student objects
     * after they generate an answer.
     * @param student the student to accept the answer from
     * @param answer the student's answer
     */
    public void acceptAnswer(Student student, String answer);

    /**
     * Outputs statistics on how many people chose various options.
     */
    public void outputStatistics();

    /**
     * Clears the associated data of the voting service
     */
    public void clear();

}
