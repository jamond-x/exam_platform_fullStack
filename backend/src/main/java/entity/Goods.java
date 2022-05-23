package entity;

public class Goods {
  private String name;
  private String id;
  private String description;
  private String storeId;
  private int placeOfShipment;
  private String brand;
  private String imgUrl;
  private  String comment;
  private int price;

  public Goods(){}

  public Goods(String id){
    this.id = id;
  }

  public Goods(String name, String id, String description, String storeId, int placeOfShipment, String brand, String imgUrl, String comment, int price) {
    this.name = name;
    this.id = id;
    this.description = description;
    this.storeId = storeId;
    this.placeOfShipment = placeOfShipment;
    this.brand = brand;
    this.imgUrl = imgUrl;
    this.comment = comment;
    this.price = price;
  }

  public Goods(String name, String id, String description, String storeId, int price) {
    this.name = name;
    this.id = id;
    this.description = description;
    this.storeId = storeId;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getStoreId() {
    return storeId;
  }

  public int getPlaceOfShipment() {
    return placeOfShipment;
  }

  public String getBrand() {
    return brand;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public String getComment() {
    return comment;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "Goods{" +
      "name='" + name + '\'' +
      ", id='" + id + '\'' +
      ", description='" + description + '\'' +
      ", storeId='" + storeId + '\'' +
      ", placeOfShipment=" + placeOfShipment +
      ", brand='" + brand + '\'' +
      ", imgUrl='" + imgUrl + '\'' +
      ", comment='" + comment + '\'' +
      ", price=" + price +
      '}';
  }
}
