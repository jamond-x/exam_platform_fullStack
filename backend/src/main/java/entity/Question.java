package entity;

public class Question {
  private String id;
  private int typeId;
  private String name;
  private String description;
  private String A;
  private String B;
  private String C;
  private String D;
  private String answer;

  public Question(){};

  public Question(String id, int typeId, String description, String a, String b, String c, String d, String answer, String name) {
    this.id = id;
    this.typeId = typeId;
    this.description = description;
    A = a;
    B = b;
    C = c;
    D = d;
    this.answer = answer;
    this.name = name;
  }

  public Question(String id, int typeId, String description, String a, String b, String answer) {
    this.id = id;
    this.typeId = typeId;
    this.description = description;
    A = a;
    B = b;
    this.answer = answer;
  }

  public Question(String id) {
    this.id = id;
  }

  public Question(int typeId) {
    this.typeId = typeId;
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

  public String getA() {
    return A;
  }

  public String getB() {
    return B;
  }

  public String getC() {
    return C;
  }

  public String getD() {
    return D;
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
      ", A='" + A + '\'' +
      ", B='" + B + '\'' +
      ", C='" + C + '\'' +
      ", D='" + D + '\'' +
      ", answer='" + answer + '\'' +
      '}';
  }
}
