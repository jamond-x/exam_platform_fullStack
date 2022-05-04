package entity;

public class Question {
  private String id;
  private int typeId;
  private String name;
  private String description;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;
  private String answer;

  public Question(){};

  public Question(String id) {
    this.id = id;
  }

  public Question(int typeId) {
    this.typeId = typeId;
  }

  public Question(String id, int typeId, String name, String description, String optionA, String optionB, String optionC, String optionD, String answer) {
    this.id = id;
    this.typeId = typeId;
    this.name = name;
    this.description = description;
    this.optionA = optionA;
    this.optionB = optionB;
    this.optionC = optionC;
    this.optionD = optionD;
    this.answer = answer;
  }

  public Question(String id, int typeId, String description, String optionA, String optionB, String optionC, String optionD, String answer) {
    this.id = id;
    this.typeId = typeId;
    this.description = description;
    this.optionA = optionA;
    this.optionB = optionB;
    this.optionC = optionC;
    this.optionD = optionD;
    this.answer = answer;
  }


  public String getId() {
    return id;
  }

  public int getTypeId() {
    return typeId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getOptionA() {
    return optionA;
  }

  public String getOptionB() {
    return optionB;
  }

  public String getOptionC() {
    return optionC;
  }

  public String getOptionD() {
    return optionD;
  }

  public String getAnswer() {
    return answer;
  }

  @Override
  public String toString() {
    return "Question{" +
      "id='" + id + '\'' +
      ", typeId=" + typeId +
      ", name='" + name + '\'' +
      ", description='" + description + '\'' +
      ", optionA='" + optionA + '\'' +
      ", optionB='" + optionB + '\'' +
      ", optionC='" + optionC + '\'' +
      ", optionD='" + optionD + '\'' +
      ", answer='" + answer + '\'' +
      '}';
  }
}
