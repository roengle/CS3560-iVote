public interface StudentInterface {
    /**
     * Generates answers based on the conditions of the question type.
     */
    public void generateAnswers();

    /**
     * Submits answers by calling methods in VotingService
     */
    public void submitAnswers();

    /**
     * Gets student ID.
     * @return the ID of the student
     */
    public int getID();
}
